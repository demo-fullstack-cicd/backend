package _2024.winter.demopico.domain.reply.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.reply.dto.request.UpdateReplyRequest;
import _2024.winter.demopico.domain.reply.dto.request.UploadReplyRequest;
import _2024.winter.demopico.domain.reply.dto.response.DeleteReplyResponse;
import _2024.winter.demopico.domain.reply.dto.response.UpdateReplyResponse;
import _2024.winter.demopico.domain.reply.dto.response.UploadReplyResponse;
import _2024.winter.demopico.domain.reply.service.ReplyApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReplyController {

    private final ReplyApplicationService replyApplicationService;

    // 대댓글 달기
    @PostMapping("/feeds/{feedId}/comments/{commentId}/replies")
    public SuccessApiResponse<UploadReplyResponse> uploadReply(
            @RequestBody UploadReplyRequest uploadReplyRequest,
            @PathVariable("feedId") Long feedId,
            @PathVariable("commentId") Long commentId,
            HttpServletRequest httpRequest)
    {
        log.info("[ReplyController - uploadReply]  uploadReplyRequest = {}, feedId = {}, commentId = {}", uploadReplyRequest, feedId, commentId);
        return SuccessApiResponse.onSuccessUploadReply(replyApplicationService.uploadReply(uploadReplyRequest, feedId, commentId, httpRequest));
    }

    // 대댓글 수정
    @PatchMapping("/feeds/{feedId}/comments/{commentId}/replies/{replyId}")
    public SuccessApiResponse<UpdateReplyResponse> updateReply(
            @RequestBody UpdateReplyRequest updateReplyRequest,
            @PathVariable("feedId") Long feedId,
            @PathVariable("commentId") Long commentId,
            @PathVariable("replyId") Long replyId,
            HttpServletRequest httpRequest)
    {
        log.info("[ReplyController - updateReply]  updateReplyRequest = {}, feedId = {}, commentId = {}, replyId = {}", updateReplyRequest, feedId, commentId, replyId);
        return SuccessApiResponse.onSuccessUpdateReply(replyApplicationService.updateReply(updateReplyRequest, feedId, commentId, replyId, httpRequest));
    }

    // 대댓글 삭제
    @DeleteMapping("/feeds/{feedId}/comments/{commentId}/replies/{replyId}")
    public SuccessApiResponse<Void> deleteReply(
            @PathVariable("feedId") Long feedId,
            @PathVariable("commentId") Long commentId,
            @PathVariable("replyId") Long replyId,
            HttpServletRequest httpRequest)
    {
        log.info("[ReplyController - deleteReply]  feedId = {}, commentId = {}, replyId = {}",feedId, commentId, replyId);
        replyApplicationService.deleteReply(feedId, commentId, replyId, httpRequest);
        return SuccessApiResponse.onSuccessDeleteReply();
    }
}
