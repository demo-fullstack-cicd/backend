package _2024.winter.demopico.domain.user.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.user.dto.request.UpdateBioRequest;
import _2024.winter.demopico.domain.user.dto.response.GetInfoResponse;
import _2024.winter.demopico.domain.user.dto.response.UpdateBioResponse;
import _2024.winter.demopico.domain.user.service.UserApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class InfoController {

    private final UserApplicationService userApplicationService;

    @GetMapping("/info")
    public SuccessApiResponse<GetInfoResponse> getInfo(
            HttpServletRequest httpServletRequest)
    {
        log.info("[InfoController - getInfo]");
        return SuccessApiResponse.onSuccessGetInfo(userApplicationService.getInfo(httpServletRequest));
    }

    @PostMapping("/info/bio")
    public SuccessApiResponse<UpdateBioResponse> updateBio(
            @RequestBody UpdateBioRequest request,
            HttpServletRequest httpServletRequest)
    {
        log.info("[InfoController - updateBio] request = {}", request);
        return SuccessApiResponse.onSuccessUpdateBio(userApplicationService.updateBio(request, httpServletRequest));
    }

}
