package _2024.winter.demopico.domain.image.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.ImageException;
import _2024.winter.demopico.domain.image.dto.ImageInfoDto;
import _2024.winter.demopico.domain.image.dto.response.GetImagesResponse;
import _2024.winter.demopico.domain.image.dto.response.GetPresignedUrlToDownloadResponse;
import _2024.winter.demopico.domain.image.dto.response.GetPresignedUrlToUploadResponse;
import _2024.winter.demopico.domain.image.entity.Image;
import _2024.winter.demopico.domain.image.repository.ImageRepository;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
@RequiredArgsConstructor
@Slf4j
public class ImageQueryService {
    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public GetPresignedUrlToUploadResponse getPresignedUrlToUpload(String imageName) {
        log.info("[ImageQueryService - getPresignedUrlToUpload]");
        /// 제한시간 설정
        Date expiration = new Date();
        long expTime = expiration.getTime();
        expTime += TimeUnit.MINUTES.toMillis(3); // 3 Minute
        expiration.setTime(expTime);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, imageName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration);

        return GetPresignedUrlToUploadResponse.builder()
                .presignedUrl(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString())
                .build();

    }

    public GetPresignedUrlToDownloadResponse getPresignedUrlToDownload(String imageName) {
        log.info("[ImageQueryService - getPresignedUrlToDownload]");
        /// 제한시간 설정
        Date expiration = new Date();
        long expTime = expiration.getTime();
        expTime += TimeUnit.MINUTES.toMillis(3);
        expiration.setTime(expTime); // 3 Minute

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, imageName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);

        return GetPresignedUrlToDownloadResponse.builder()
                .presignedUrl(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString())
                .build();
    }

    public GetImagesResponse getImages() {
        log.info("[ImageQueryService - getImages]");
        ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
                .withBucketName(bucket);

        ListObjectsV2Result result = amazonS3.listObjectsV2(listObjectsRequest);

        List<ImageInfoDto> imageInfoDtos = result.getObjectSummaries().stream()
                .map(this::convertToImageInfoDto)
                .toList();

        return GetImagesResponse.builder()
                .images(imageInfoDtos)
                .build();
    }
    private ImageInfoDto convertToImageInfoDto(S3ObjectSummary s3ObjectSummary) {
        String imageName = s3ObjectSummary.getKey();
        Image image = imageRepository.findByImageName(imageName).orElseThrow(ImageException.ImageNotExistException::new);
        return new ImageInfoDto(image.getId(), image.getImageName(), image.getUploadedAt());
    }
}
