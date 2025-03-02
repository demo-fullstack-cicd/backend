package _2024.winter.demopico.domain.study.service;

import _2024.winter.demopico.domain.study.dto.request.UpdateStudyRequest;
import _2024.winter.demopico.domain.study.dto.request.UploadStudyRequest;
import _2024.winter.demopico.domain.study.dto.response.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyApplicationService {
    private final StudyCommandService studyCommandService;
    private final StudyQueryService studyQueryService;

    public GetStudiesResponse getStudies(int joinYear, int joinSemester){
        return studyQueryService.getStudies(joinYear, joinSemester);
    }

    public GetOneStudyResponse getOneStudy(Long studyId) {
        return studyQueryService.getOneStudy(studyId);
    }

    public UploadStudyResponse uploadStudy(UploadStudyRequest request){
        return studyCommandService.uploadStudy(request);
    }

    public UpdateStudyResponse updateStudy(Long studyId, UpdateStudyRequest request, HttpServletRequest httpServletRequest){
        return studyCommandService.updateStudy(studyId, request, httpServletRequest);
    }

    public void deleteStudy(Long studyId, HttpServletRequest httpServletRequest){
        studyCommandService.deleteStudy(studyId, httpServletRequest);
    }
}
