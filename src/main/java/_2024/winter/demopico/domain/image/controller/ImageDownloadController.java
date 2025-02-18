package _2024.winter.demopico.domain.image.controller;

import _2024.winter.demopico.domain.image.dto.DownloadPresignedUrlDto;
import _2024.winter.demopico.domain.image.service.ImageService;
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
    private final ImageService imageService;

    @GetMapping("/image/presignedUrl/download")
    public DownloadPresignedUrlDto getPresignedUrlToDownload(
            @RequestParam(value = "imageName") String imageName)
    {
        log.info("imageName: " + imageName);
        return imageService.getPresignedUrlToDownload(imageName);
    }
}
