package _2024.winter.demopico.domain.comment.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.comment.dto.request.UpdateCommentRequest;
import _2024.winter.demopico.domain.comment.dto.request.UploadCommentRequest;
import _2024.winter.demopico.domain.comment.dto.response.DeleteCommentResponse;
import _2024.winter.demopico.domain.comment.dto.response.GetCommentsResponse;
import _2024.winter.demopico.domain.comment.dto.response.UpdateCommentResponse;
import _2024.winter.demopico.domain.comment.dto.response.UploadCommentResponse;
import _2024.winter.demopico.domain.comment.service.CommentApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentApplicationService commentApplicationService;

    // 댓글 달기
    @PostMapping("/feeds/{feedId}/comments")
    public SuccessApiResponse<UploadCommentResponse> uploadComment(
            @RequestBody UploadCommentRequest uploadCommentRequest,
            @PathVariable("feedId") Long feedId,
            HttpServletRequest httpRequest)
    {
        log.info("[CommentController - uploadComment]  uploadCommentRequest = {}, feedId = {}", uploadCommentRequest, feedId);
        return SuccessApiResponse.onSuccessUploadComment(commentApplicationService.uploadComment(uploadCommentRequest, feedId, httpRequest));
    }

    // 댓글 수정
    @PatchMapping("/feeds/{feedId}/comments/{commentId}")
    public SuccessApiResponse<UpdateCommentResponse> updateComment(
            @RequestBody UpdateCommentRequest updateCommentRequest,
            @PathVariable("feedId") Long feedId,
            @PathVariable("commentId") Long commentId,
            HttpServletRequest httpRequest)
    {
        log.info("[CommentController - updateComment]  updateCommentRequest = {}, feedId = {}, commentId = {}", updateCommentRequest, feedId, commentId);
        return SuccessApiResponse.onSuccessUpdateComment(commentApplicationService.updateComment(updateCommentRequest, feedId, commentId, httpRequest));
    }

    // 댓글 삭제
    @DeleteMapping("/feeds/{feedId}/comments/{commentId}")
    public SuccessApiResponse<Void> deleteComment(
            @PathVariable("feedId") Long feedId,
            @PathVariable("commentId") Long commentId,
            HttpServletRequest httpRequest)
    {
        log.info("[CommentController - deleteComment]  feedId = {}, commentId = {}",feedId, commentId);
        commentApplicationService.deleteComment(feedId, commentId, httpRequest);
        return SuccessApiResponse.onSuccessDeleteComment();
    }

    // 댓글 조회
    @GetMapping("/feeds/{feedId}/comments")
    public SuccessApiResponse<GetCommentsResponse> getComments(
            @PathVariable("feedId") Long feedId)
    {
        log.info("[CommentController - deleteComment]  feedId = {}", feedId);
        return SuccessApiResponse.onSuccessGetComments(commentApplicationService.getComments(feedId));
    }
}
