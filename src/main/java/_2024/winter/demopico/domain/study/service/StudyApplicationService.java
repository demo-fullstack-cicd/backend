package _2024.winter.demopico.domain.study.service;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.study.dto.request.UpdateStudyRequest;
import _2024.winter.demopico.domain.study.dto.request.UploadStudyRequest;
import _2024.winter.demopico.domain.study.dto.response.GetOneStudyResponse;
import _2024.winter.demopico.domain.study.dto.response.GetStudiesResponse;
import _2024.winter.demopico.domain.study.dto.response.UpdateStudyResponse;
import _2024.winter.demopico.domain.study.dto.response.UploadStudyResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
}
