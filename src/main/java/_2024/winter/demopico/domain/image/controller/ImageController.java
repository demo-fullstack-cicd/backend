package _2024.winter.demopico.domain.image.controller;

import _2024.winter.demopico.domain.image.dto.GetImageResponse;
import _2024.winter.demopico.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/images")
    public List<GetImageResponse> getImages(){
        return imageService.getImages();
    }

}
