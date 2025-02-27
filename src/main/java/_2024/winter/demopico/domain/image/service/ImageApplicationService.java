package _2024.winter.demopico.domain.image.service;

import _2024.winter.demopico.domain.image.dto.request.ImageUploadRequest;
import _2024.winter.demopico.domain.image.dto.response.GetImagesResponse;
import _2024.winter.demopico.domain.image.dto.response.GetPresignedUrlToDownloadResponse;
import _2024.winter.demopico.domain.image.dto.response.GetPresignedUrlToUploadResponse;
import _2024.winter.demopico.domain.image.dto.response.UploadImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageApplicationService {

    private final ImageCommandService imageCommandService;
    private final ImageQueryService imageQueryService;

    public GetPresignedUrlToUploadResponse getPresignedUrlToUpload(String imageName){
        return imageQueryService.getPresignedUrlToUpload(imageName);
    }

    public UploadImageResponse uploadImage(ImageUploadRequest request){
        return imageCommandService.uploadImage(request);
    }

    public GetPresignedUrlToDownloadResponse getPresignedUrlToDownload(String imageName){
        return imageQueryService.getPresignedUrlToDownload(imageName);
    }

    public GetImagesResponse getImages(){
        return imageQueryService.getImages();
    }
}
