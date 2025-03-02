package _2024.winter.demopico.domain.study.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.StudyException;
import _2024.winter.demopico.domain.study.dto.StudyBriefDto;
import _2024.winter.demopico.domain.study.dto.StudyParticipantBriefDto;
import _2024.winter.demopico.domain.study.dto.response.GetOneStudyResponse;
import _2024.winter.demopico.domain.study.dto.response.GetStudiesResponse;
import _2024.winter.demopico.domain.study.entity.Study;
import _2024.winter.demopico.domain.study.repository.StudyRepository;
import _2024.winter.demopico.domain.study.entity.StudyParticipant;
import _2024.winter.demopico.domain.study.repository.StudyParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StudyQueryService {
    private final StudyRepository studyRepository;
    private final StudyParticipantRepository studyParticipantRepository;

    public GetStudiesResponse getStudies(int joinYear, int joinSemester){
        log.info("[StudyQueryService - getStudies]");
        List<Study> studies = studyRepository.findByJoinYearAndJoinSemester(joinYear, joinSemester);

        List<StudyBriefDto> studyBriefDtos =  studies.stream()
                .map(StudyBriefDto::new)
                .toList();

        return GetStudiesResponse.builder()
                .studies(studyBriefDtos)
                .build();
    }

    public GetOneStudyResponse getOneStudy(Long studyId) {
        log.info("[StudyQueryService - getOneStudy]");
        Study study = studyRepository.findById(studyId).orElseThrow(StudyException.StudyNotExistException::new);

        List<StudyParticipant> studyParticipants = studyParticipantRepository.findAllByStudy(study);
        List<StudyParticipantBriefDto> studyParticipantBriefDtos = studyParticipants.stream().map(StudyParticipantBriefDto::new).toList();

        return GetOneStudyResponse.builder()
                .id(study.getId())
                .title(study.getTitle())
                .description(study.getDescription())
                .capacity(study.getCapacity())
                .currentParticipants(study.getCurrentParticipants())
                .location(study.getLocation())
                .organizer(study.getOrganizer())
                .joinYear(study.getJoinYear())
                .joinSemester(study.getJoinSemester())
                .status(study.getStatus())
                .studyParticipants(studyParticipantBriefDtos)
                .build();
    }
}
