package _2024.winter.demopico.domain.user.service;

import _2024.winter.demopico.domain.user.entity.User;
import _2024.winter.demopico.domain.user.dto.CheckUsernameRequest;
import _2024.winter.demopico.domain.user.dto.LoginRequest;
import _2024.winter.demopico.domain.user.dto.RegisterRequest;
import _2024.winter.demopico.domain.user.repository.UserRepository;
import _2024.winter.demopico.domain.user.util.CookieUtil;
import _2024.winter.demopico.domain.user.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate stringRedisTemplate;
    private final JWTUtil jwtUtil;

    /* register */
    // username 중복 확인
    public ResponseEntity<String> checkUsernameDuplicate(CheckUsernameRequest request){
        log.info("[UserService - checkUsernameDuplicate]");

        if (userRepository.existsByUsername(request.getUsername())){
            System.out.println(userRepository.existsByUsername(request.getUsername()));
            throw new RuntimeException("duplicated username");
        }
        System.out.println("no err in username " + request.getUsername());
        return ResponseEntity.ok().body(request.getUsername());
    }

    // 회원 저장
    public ResponseEntity<Long> register(RegisterRequest request){
        log.info("[UserService - register]");

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("duplicated email address");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role("ROLE_USER")
                .build();

        userRepository.saveAndFlush(user);

        return ResponseEntity.ok().body(user.getId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    /* login */
    // 로그인
    public ResponseEntity<Long> login(LoginRequest request, HttpServletResponse httpServletResponse){
        log.info("[UserService - login]");

        // 1. username 확인
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(RuntimeException::new);

        // 2. password 일치 확인
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("비밀번호 불일치");
        }

        // 3. jwt token 발급 및 redis 저장
        String access = jwtUtil.createJwt("access", user.getUsername(), user.getRole(), 10 * 60 * 1000L);
        String refresh = jwtUtil.createJwt("refresh", user.getUsername(), user.getRole(), 24 * 60 * 60 * 1000L);

        String redisRefreshKey = "refresh:userId:" + user.getId();
        stringRedisTemplate.opsForValue().set(redisRefreshKey, refresh, 1, TimeUnit.DAYS);

        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Access, Location");
        httpServletResponse.setHeader("access", access);
        httpServletResponse.addCookie(CookieUtil.createCookie("refresh", refresh));

        return ResponseEntity.ok().body(user.getId());

    }


    // ----------------------------------------------------------------------------------------------------------------
    public ResponseEntity<Long> reissue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        log.info("[UserService - reissue]");

        // cookie refresh 추출
        String cookieRefresh = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("refresh")){
                cookieRefresh = cookie.getValue();
            }
        }

        // redis refresh 추출
        User user = userRepository.findByUsername(jwtUtil.getUsername(cookieRefresh)).orElseThrow(RuntimeException::new);
        String redisRefreshKey = "refresh:userId:" + user.getId();
        String redisRefreshValue = stringRedisTemplate.opsForValue().get(redisRefreshKey);

        // refresh 유효성 검증
        if (!jwtUtil.isValidRefreshToken(cookieRefresh, redisRefreshValue)){
            throw new RuntimeException();
        }

        // redis refresh 삭제
        stringRedisTemplate.delete(redisRefreshKey);

        // access, refresh 모두 재발급
        String newAccess = jwtUtil.createJwt("access", user.getUsername(), jwtUtil.getRole(cookieRefresh), 10 * 60 * 1000L);
        String newRefresh = jwtUtil.createJwt("refresh", user.getUsername(), jwtUtil.getRole(cookieRefresh), 24 * 60 * 60 * 1000L);

        // new refresh 1 day 유지
        stringRedisTemplate.opsForValue().set(redisRefreshKey, newRefresh, 1, TimeUnit.DAYS);

        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Access, Location");
        httpServletResponse.setHeader("access", newAccess);
        httpServletResponse.addCookie(CookieUtil.createCookie("refresh", newRefresh));


        // get localUser for return
        User reissuedUser =  userRepository.findByUsername(user.getUsername()).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok().body(reissuedUser.getId());

    }
}
