package _2024.winter.demopico.domain.image.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.image.dto.response.GetImagesResponse;
import _2024.winter.demopico.domain.image.service.ImageApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {
    private final ImageApplicationService imageApplicationService;

    @GetMapping("/images")
    public SuccessApiResponse<GetImagesResponse> getImages(){
        log.info("[ImageController - getImages]");
        return SuccessApiResponse.onSuccessGetImages(imageApplicationService.getImages());
    }

}
