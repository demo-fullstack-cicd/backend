package _2024.winter.demopico.domain.image.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.image.dto.request.ImageUploadRequest;
import _2024.winter.demopico.domain.image.dto.response.GetPresignedUrlToUploadResponse;
import _2024.winter.demopico.domain.image.dto.response.UploadImageResponse;
import _2024.winter.demopico.domain.image.service.ImageApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageUploadController {

    private final ImageApplicationService imageApplicationService;

    @GetMapping("/image/presignedUrl/upload")
    public SuccessApiResponse<GetPresignedUrlToUploadResponse> getPresignedUrlToUpload(
            @RequestParam(value = "imageName") String imageName)
    {
        log.info("[ImageUploadController - getPresignedUrlToUpload]");
        return SuccessApiResponse.onSuccessGetPresignedUrlToUpload(imageApplicationService.getPresignedUrlToUpload(imageName));
    }

    @PostMapping("/images/presignedUrl/upload")
    public SuccessApiResponse<UploadImageResponse> uploadImage(@RequestBody ImageUploadRequest request)
    {
        log.info("[ImageUploadController - uploadImage]");
        return SuccessApiResponse.onSuccessUploadImage(imageApplicationService.uploadImage(request));
    }
}
