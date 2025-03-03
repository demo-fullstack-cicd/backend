package _2024.winter.demopico.domain.user.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.user.dto.request.LoginRequest;
import _2024.winter.demopico.domain.user.dto.response.LoginResponse;
import _2024.winter.demopico.domain.user.service.UserApplicationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final UserApplicationService userApplicationService;

    @PostMapping("/login")
    public SuccessApiResponse<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse httpServletResponse) {
        log.info("[LoginController - login] request.username = {},request.password = {} ", request.getUsername(), request.getPassword());

        return SuccessApiResponse.onSuccessLogin(userApplicationService.login(request, httpServletResponse));
    }
}
