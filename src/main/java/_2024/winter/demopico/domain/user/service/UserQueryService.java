package _2024.winter.demopico.domain.user.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.UserException;
import _2024.winter.demopico.domain.user.dto.request.CheckUsernameDuplicateRequest;
import _2024.winter.demopico.domain.user.dto.request.SendAuthEmailRequest;
import _2024.winter.demopico.domain.user.dto.response.CheckUsernameDuplicateResponse;
import _2024.winter.demopico.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserQueryService {
    private final UserRepository userRepository;

    // 사용자이름 중복 확인
    public CheckUsernameDuplicateResponse checkUsernameDuplicate(CheckUsernameDuplicateRequest request){
        log.info("[UserQueryService - checkUsernameDuplicate]");


        if(userRepository.existsByUsername(request.getUsername())){
            throw new UserException.UsernameDuplicateException();
        }

        return CheckUsernameDuplicateResponse.builder()
                .username(request.getUsername())
                .build();
    }

    // 이메일 중복 확인
    public void checkEmailDuplicate(SendAuthEmailRequest request){
        log.info("[UserQueryService - checkEmailDuplicate]");

        if (userRepository.existsByEmail(request.getEmail())){
            throw new UserException.EmailDuplicateException();
        }
    }


}
