package _2024.winter.demopico.domain.image.controller;

import _2024.winter.demopico.domain.image.dto.ImageUploadSuccessRequest;
import _2024.winter.demopico.domain.image.dto.UploadPresignedUrlDto;
import _2024.winter.demopico.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageUploadController {

    private final ImageService imageService;

    @GetMapping("/image/presignedUrl/upload")
    public UploadPresignedUrlDto getPresignedUrlToUpload(
            @RequestParam(value = "imageName") String imageName)
    {
        log.info("[ImageUploadController - getPresignedUrlToUpload]");
        return imageService.getPresignedUrlToUpload(imageName);
    }

    @PostMapping("/images/presignedUrl/upload")
    public ResponseEntity<Long> uploadSuccess(@RequestBody ImageUploadSuccessRequest request)
    {
        log.info("[ImageUploadController - uploadSuccess]");
        return imageService.saveImageMetadata(request);
    }
}
