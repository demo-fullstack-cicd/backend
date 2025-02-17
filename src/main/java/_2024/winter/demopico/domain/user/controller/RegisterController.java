package _2024.winter.demopico.domain.user.controller;

import _2024.winter.demopico.domain.user.dto.CheckAuthEmailRequest;
import _2024.winter.demopico.domain.user.dto.CheckUsernameRequest;
import _2024.winter.demopico.domain.user.dto.SendAuthEmailRequest;
import _2024.winter.demopico.domain.user.dto.RegisterRequest;
import _2024.winter.demopico.domain.user.service.EmailService;
import _2024.winter.demopico.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final EmailService emailService;
    private final UserService userService;

    // 1. username 중복 확인
    @PostMapping("/check/username")
    public ResponseEntity<String> checkUsernameDuplicate(@RequestBody CheckUsernameRequest request){
        log.info("[RegisterController - checkUsernameDuplicate] request.username = {}", request.getUsername());

        return userService.checkUsernameDuplicate(request);
    }

    // 2. auth-email 전송
    @PostMapping("/email/send")
    public ResponseEntity<String> sendAuthEmail(@RequestBody SendAuthEmailRequest request) {
        log.info("[[RegisterController - AuthEmail send] request.email = {}", request.getEmail());

        return emailService.sendAuthEmail(request);
    }

    // 3. auth-email code 검증
    @PostMapping("/email/check")
    public ResponseEntity<String> checkAuthEmail(@RequestBody CheckAuthEmailRequest request) {
        log.info("[[RegisterController - AuthEmail check] request.email = {}, request.verificationCode = {}", request.getEmail(), request.getVerificationCode());

        return emailService.checkAuthEmail(request);
    }

    // 4. 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Long> register(@RequestBody RegisterRequest request){
        log.info("[RegisterController - register] request.username = {}, request.password = {}, request.email = {}", request.getUsername(), request.getPassword(), request.getEmail());

        return userService.register(request);
    }
}
