package _2024.winter.demopico.domain.image.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.image.dto.response.GetPresignedUrlToDownloadResponse;
import _2024.winter.demopico.domain.image.service.ImageApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageDownloadController {
    private final ImageApplicationService imageApplicationService;

    @GetMapping("/image/presignedUrl/download")
    public SuccessApiResponse<GetPresignedUrlToDownloadResponse> getPresignedUrlToDownload(
            @RequestParam(value = "imageName") String imageName)
    {
        log.info("[ImageUploadController - getPresignedUrlToUpload] imageName = {}", imageName);
        return SuccessApiResponse.onSuccessGetPresignedUrlToDownload(imageApplicationService.getPresignedUrlToDownload(imageName));
    }
}
