package _2024.winter.demopico.domain.image.service;

import _2024.winter.demopico.domain.image.dto.DownloadPresignedUrlDto;
import _2024.winter.demopico.domain.image.dto.GetImageResponse;
import _2024.winter.demopico.domain.image.dto.ImageUploadSuccessRequest;
import _2024.winter.demopico.domain.image.dto.UploadPresignedUrlDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public UploadPresignedUrlDto getPresignedUrlToUpload(String imageName) {
        /// 제한시간 설정
        Date expiration = new Date();
        long expTime = expiration.getTime();
        expTime += TimeUnit.MINUTES.toMillis(3); // 3 Minute
        expiration.setTime(expTime);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, imageName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration);

        return UploadPresignedUrlDto.builder()
                .presignedUrl(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString())
                .build();

    }

    public ResponseEntity<Long> saveImageMetadata(ImageUploadSuccessRequest request) {
        String imageUrl = amazonS3.getUrl(bucket, request.getImageName()).toString();
        System.out.println( request.getImageName());

        Image image = Image.builder()
                .imageName(request.getImageName())
                .imageUrl(imageUrl)
                .uploadedAt(LocalDateTime.now())
                .build();

        imageRepository.saveAndFlush(image);

        return ResponseEntity.ok().body(image.getId());
    }
    // -------------------------------------------------------------------------------------

    public DownloadPresignedUrlDto getPresignedUrlToDownload(String imageName) {
        /// 제한시간 설정
        Date expiration = new Date();
        long expTime = expiration.getTime();
        expTime += TimeUnit.MINUTES.toMillis(3);
        expiration.setTime(expTime); // 3 Minute

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, imageName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);

        log.info(imageName);

        return DownloadPresignedUrlDto.builder()
                .presignedUrl(amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString())
                .build();
    }

    // -------------------------------------------------------------------------------------
    public List<GetImageResponse> getImages() {
        log.info("[ImageService - getImages]");
        ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
                .withBucketName(bucket);

        ListObjectsV2Result result = amazonS3.listObjectsV2(listObjectsRequest);

        System.out.println(result.getObjectSummaries());

        return result.getObjectSummaries().stream()
                .map(this::convertToImageResponse)
                .collect(Collectors.toList());
        // ..
    }

    private GetImageResponse convertToImageResponse(S3ObjectSummary s3ObjectSummary) {
        String imageName = s3ObjectSummary.getKey();
        System.out.println("imageName before= " + imageName);
        Image image = imageRepository.findByImageName(imageName).orElseThrow(RuntimeException::new);
        System.out.println("image.getImageName() = " + image.getImageName());
        return new GetImageResponse(imageName, image.getUploadedAt());
    }

}
