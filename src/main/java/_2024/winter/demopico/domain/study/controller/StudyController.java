package _2024.winter.demopico.domain.study.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.study.dto.request.UpdateStudyRequest;
import _2024.winter.demopico.domain.study.dto.request.UploadStudyRequest;
import _2024.winter.demopico.domain.study.dto.response.GetOneStudyResponse;
import _2024.winter.demopico.domain.study.dto.response.GetStudiesResponse;
import _2024.winter.demopico.domain.study.dto.response.UpdateStudyResponse;
import _2024.winter.demopico.domain.study.dto.response.UploadStudyResponse;
import _2024.winter.demopico.domain.study.service.StudyApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudyController {
    private final StudyApplicationService studyApplicationService;

    // 스터디 목록 조회
    @GetMapping("/studies")
    public SuccessApiResponse<GetStudiesResponse> getStudies(
            @RequestParam(name = "joinYear") int joinYear,
            @RequestParam(name = "joinSemester") int joinSemester)
    {
        log.info("[StudyController - getStudies] joinYear = {}, joinSemester = {}", joinYear, joinSemester);
        return SuccessApiResponse.onSuccessGetStudies(studyApplicationService.getStudies(joinYear, joinSemester));
    }

    // 스터디 세부 조회
    @GetMapping("/studies/{studyId}")
    public SuccessApiResponse<GetOneStudyResponse> getOneStudy(
            @PathVariable(name = "studyId") Long studyId)
    {
        log.info("[StudyController - getOneStudy] studyId = {}", studyId);
        return SuccessApiResponse.onSuccessGetOneStudy(studyApplicationService.getOneStudy(studyId));
    }

    // 스터디 업로드
    @PostMapping("/studies")
    public SuccessApiResponse<UploadStudyResponse> uploadStudy(
            @RequestBody UploadStudyRequest request)
    {
        log.info("[StudyController - uploadStudy] request = {}", request);
        return SuccessApiResponse.onSuccessUploadStudy(studyApplicationService.uploadStudy(request));
    }

    // 스터디 수정
    @PatchMapping("/studies/{studyId}")
    public SuccessApiResponse<UpdateStudyResponse> updateStudy(
            @PathVariable(name = "studyId") Long studyId,
            @RequestBody UpdateStudyRequest request,
            HttpServletRequest httpServletRequest)
    {
        log.info("[StudyController - updateStudy] studyId = {}, request = {}", studyId, request);
        return SuccessApiResponse.onSuccessUpdateStudy(studyApplicationService.updateStudy(studyId, request, httpServletRequest));
    }

}
