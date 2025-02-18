package _2024.winter.demopico.domain.user.controller;

import _2024.winter.demopico.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReissueController {

    private final UserService userService;

    @PostMapping("/reissue")
    public ResponseEntity<Long> reissue(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        log.info("[ReissueController - refresh]");

        return userService.reissue(httpRequest, httpResponse);
    }
}
