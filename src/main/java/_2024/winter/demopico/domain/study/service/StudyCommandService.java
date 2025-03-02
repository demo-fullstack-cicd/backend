package _2024.winter.demopico.domain.study.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.StudyException;
import _2024.winter.demopico.common.apiPayload.failure.customException.UserException;
import _2024.winter.demopico.domain.study.dto.request.UpdateStudyRequest;
import _2024.winter.demopico.domain.study.dto.request.UploadStudyRequest;
import _2024.winter.demopico.domain.study.dto.response.UpdateStudyResponse;
import _2024.winter.demopico.domain.study.dto.response.UploadStudyResponse;
import _2024.winter.demopico.domain.study.entity.Study;
import _2024.winter.demopico.domain.study.repository.StudyRepository;
import _2024.winter.demopico.domain.study.entity.StudyParticipant;
import _2024.winter.demopico.domain.study.entity.StudyParticipantRole;
import _2024.winter.demopico.domain.study.repository.StudyParticipantRepository;
import _2024.winter.demopico.domain.user.entity.User;
import _2024.winter.demopico.domain.user.repository.UserRepository;
import _2024.winter.demopico.domain.user.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StudyCommandService {
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudyParticipantRepository studyParticipantRepository;
    private final JWTUtil jwtUtil;

    public UploadStudyResponse uploadStudy(UploadStudyRequest request){
        log.info("[StudyCommandService - uploadStudy]");

        Study study = Study.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .capacity(request.getCapacity())
                .currentParticipants(request.getCurrentParticipants())
                .location(request.getLocation())
                .organizer(request.getOrganizer())
                .joinYear(request.getJoinYear())
                .joinSemester(request.getJoinSemester())
                .status(request.getStatus())
                .document(request.getDocument())
                .schedule(request.getSchedule())
                .build();

        studyRepository.saveAndFlush(study);

        return UploadStudyResponse.builder()
                .studyId(study.getId().toString())
                .build();
    }

    public UpdateStudyResponse updateStudy(Long studyId, UpdateStudyRequest request, HttpServletRequest httpServletRequest){
        log.info("[StudyCommandService - updateStudy]");
        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Study study = studyRepository.findById(studyId).orElseThrow(StudyException.StudyNotExistException::new);

        if(!study.getOrganizer().equals(user.getUsername())){
            throw new StudyException.StudyNotOwnerException();
        }

        studyRepository.updateStudy(study, request);

        List<String> participants = request.getParticipants();

        for(String participatedUsername : participants){
            User participatedUser = userRepository.findByUsername(participatedUsername).orElseThrow(UserException.UsernameNotExistException::new);

            StudyParticipant studyParticipant = StudyParticipant.builder()
                    .user(participatedUser)
                    .study(study)
                    .studyParticipantRole(StudyParticipantRole.PARTICIPANT)
                    .build();
            studyParticipantRepository.save(studyParticipant);
        }

        return UpdateStudyResponse.builder()
                .studyId(study.getId().toString())
                .build();
    }

    public void deleteStudy(Long studyId, HttpServletRequest httpServletRequest){
        log.info("[StudyCommandService - deleteStudy]");

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Study study = studyRepository.findById(studyId).orElseThrow(StudyException.StudyNotExistException::new);

        if(!study.getOrganizer().equals(user.getUsername())){
            throw new StudyException.StudyNotOwnerException();
        }

        studyRepository.delete(study);
    }
}
