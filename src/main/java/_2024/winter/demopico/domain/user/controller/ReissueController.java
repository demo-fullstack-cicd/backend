package _2024.winter.demopico.domain.user.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.user.dto.response.ReissueResponse;
import _2024.winter.demopico.domain.user.service.UserApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReissueController {

    private final UserApplicationService userApplicationService;

    @PostMapping("/reissue")
    // @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @CrossOrigin(origins = "http://juhhoho.xyz", allowCredentials = "true")
    public SuccessApiResponse<ReissueResponse> reissue(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        log.info("[ReissueController - refresh]");

        return SuccessApiResponse.onSuccessReissue(userApplicationService.reissue(httpRequest, httpResponse));
    }
}
