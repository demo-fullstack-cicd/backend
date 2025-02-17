package _2024.winter.demopico.domain.user.controller;

import _2024.winter.demopico.domain.user.dto.LoginRequest;
import _2024.winter.demopico.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginRequest request, HttpServletResponse httpServletResponse) {
        log.info("[LoginController - login] request.username = {},request.password = {} ", request.getUsername(), request.getPassword());

        return userService.login(request, httpServletResponse);
    }
}
