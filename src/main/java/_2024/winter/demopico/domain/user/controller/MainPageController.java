package _2024.winter.demopico.domain.user.controller;

import _2024.winter.demopico.domain.user.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainPageController {

    private final JWTUtil jwtUtil;

    @GetMapping("/authenticated/main")
    public ResponseEntity<String> mainPage(HttpServletRequest request) {
        String username = jwtUtil.getUsername(request.getHeader("access"));

        return ResponseEntity.ok().body(username);
    }

}
