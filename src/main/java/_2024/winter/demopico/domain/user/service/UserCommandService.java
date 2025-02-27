package _2024.winter.demopico.domain.user.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.UserException;
import _2024.winter.demopico.domain.user.dto.request.LoginRequest;
import _2024.winter.demopico.domain.user.dto.request.SignupRequest;
import _2024.winter.demopico.domain.user.dto.response.LoginResponse;
import _2024.winter.demopico.domain.user.dto.response.ReissueResponse;
import _2024.winter.demopico.domain.user.dto.response.SignupResponse;
import _2024.winter.demopico.domain.user.entity.User;
import _2024.winter.demopico.domain.user.repository.UserRepository;
import _2024.winter.demopico.domain.user.util.CookieUtil;
import _2024.winter.demopico.domain.user.util.JWTUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate stringRedisTemplate;
    private final JWTUtil jwtUtil;

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String BUCKET_NAME;

    private static final String FILE_KEY = "tgwing-info.xlsx";


    // 회원가입
    public SignupResponse signup(SignupRequest request){
        log.info("[UserCommandService - signup]");

        if (!isUserValid(request)) {
            throw new UserException.UserNotClubMemberException();
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .studentId(request.getStudentId())
                .phone(request.getPhone())
                .name(request.name)
                .role("ROLE_USER")
                .build();

        userRepository.saveAndFlush(user);

        return SignupResponse.builder()
                .userId(user.getId())
                .build();
    }

    // 로그인
    public LoginResponse login(LoginRequest request, HttpServletResponse httpServletResponse){
        log.info("[UserQueryService - login]");

        // 1. username 확인
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserException.UsernameNotExistException::new);

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

        return LoginResponse.builder()
                .username(user.getUsername())
                .build();
    }

    // 토큰 재발급
    public ReissueResponse reissue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        log.info("[UserQueryService - reissue]");

        // cookie refresh 추출
        String cookieRefresh = null;
        Cookie[] cookies = httpServletRequest.getCookies();

        for (Cookie cookie : cookies){
            if (cookie.getName().equals("refresh")){
                cookieRefresh = cookie.getValue();
            }
        }

        // redis refresh 추출
        User user = userRepository.findByUsername(jwtUtil.getUsername(cookieRefresh)).orElseThrow(UserException.PasswordNotValidException::new);
        String redisRefreshKey = "refresh:userId:" + user.getId();
        String redisRefreshValue = stringRedisTemplate.opsForValue().get(redisRefreshKey);

        // refresh 유효성 검증
        if (!jwtUtil.isValidRefreshToken(cookieRefresh, redisRefreshValue)){
            throw new UserException.RefreshTokenNotValidException();
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

        return ReissueResponse.builder()
                .userId(reissuedUser.getId())
                .build();
    }

    /**
     * 🔹 S3에서 엑셀 파일을 다운로드하고, 회원 정보가 존재하는지 확인
     */
    private boolean isUserValid(SignupRequest request) {
        try (InputStream inputStream = getExcelFileFromS3()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 첫 번째 행(헤더) 스킵

                String name = getCellValue(row.getCell(0));
                String email = getCellValue(row.getCell(1));
                String phone = getCellValue(row.getCell(2));
                String studentId = getCellValue(row.getCell(3));

                // 🔹 회원 정보가 엑셀에 존재하는지 확인
                if (request.getName().equals(name) &&
                        request.getEmail().equals(email) &&
                        request.getPhone().substring(3).equals(phone) &&
                        request.getStudentId().equals(studentId)) {
                    return true; // 유효한 회원
                }

                System.out.println("request.getPhone().substring(3): " + request.getPhone().substring(3));
                System.out.println("phone                          : " + phone);

            }
        } catch (IOException e) {
            log.error("엑셀 파일을 읽는 중 오류 발생", e);
            throw new UserException.UserNotClubMemberException();
        }
        return false;
    }

    /**
     * 🔹 S3에서 엑셀 파일 다운로드
     */
    private InputStream getExcelFileFromS3() {
        S3Object s3Object = amazonS3.getObject(BUCKET_NAME, FILE_KEY);
        return s3Object.getObjectContent();
    }

    /**
     * 🔹 셀 값을 String으로 변환
     */
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue()); // 학번 등 숫자는 정수 변환
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }
}
