package _2024.winter.demopico.domain.user.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.UserException;
import _2024.winter.demopico.domain.feed.dto.FeedBriefDto;
import _2024.winter.demopico.domain.feed.repository.FeedRepository;
import _2024.winter.demopico.domain.study.dto.StudyBriefDto;
import _2024.winter.demopico.domain.study.entity.Study;
import _2024.winter.demopico.domain.study.entity.StudyParticipant;
import _2024.winter.demopico.domain.study.repository.StudyParticipantRepository;
import _2024.winter.demopico.domain.user.dto.request.CheckUsernameDuplicateRequest;
import _2024.winter.demopico.domain.user.dto.request.SendAuthEmailRequest;
import _2024.winter.demopico.domain.user.dto.response.CheckUsernameDuplicateResponse;
import _2024.winter.demopico.domain.user.dto.response.GetInfoResponse;
import _2024.winter.demopico.domain.user.entity.User;
import _2024.winter.demopico.domain.user.repository.UserRepository;
import _2024.winter.demopico.domain.user.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserQueryService {
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final JWTUtil jwtUtil;
    private final StudyParticipantRepository studyParticipantRepository;

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

    // 본인 정보 조회
    public GetInfoResponse getInfo(HttpServletRequest httpServletRequest){

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        List<FeedBriefDto> feeds = feedRepository.findByUser(user).stream()
                .map(FeedBriefDto::new)
                .toList();
        List<StudyParticipant> studyParticipants = studyParticipantRepository.findAllByUser(user);

        List<StudyBriefDto> studies = new ArrayList<>();
        for(StudyParticipant studyParticipant: studyParticipants){
            Study study = studyParticipant.getStudy();
            StudyBriefDto studyBriefDto = StudyBriefDto.builder()
                    .id(study.getId().toString())
                    .title(study.getTitle())
                    .description(study.getDescription())
                    .organizer(study.getOrganizer())
                    .capacity(study.getCapacity())
                    .currentParticipants(study.getCurrentParticipants())
                    .build();

            studies.add(studyBriefDto);
        }

        return GetInfoResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .studentId(user.getStudentId())
                .phone(user.getPhone())
                .name(user.getName())
                .feeds(feeds)
                .bio(user.getBio())
                .studies(studies)
                .build();


    }


}
