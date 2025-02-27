package _2024.winter.demopico.domain.reply.service;

import _2024.winter.demopico.domain.reply.dto.request.UpdateReplyRequest;
import _2024.winter.demopico.domain.reply.dto.request.UploadReplyRequest;
import _2024.winter.demopico.domain.reply.dto.response.DeleteReplyResponse;
import _2024.winter.demopico.domain.reply.dto.response.UpdateReplyResponse;
import _2024.winter.demopico.domain.reply.dto.response.UploadReplyResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyApplicationService {
    private final ReplyCommandService replyCommandService;

    public UploadReplyResponse uploadReply(UploadReplyRequest request, Long feedId, Long commentId, HttpServletRequest httpServletRequest){
        return replyCommandService.uploadReply(request, feedId, commentId, httpServletRequest);
    }

    public UpdateReplyResponse updateReply(UpdateReplyRequest request, Long feedId, Long commentId, Long replyId, HttpServletRequest httpServletRequest){
        return replyCommandService.updateReply(request, feedId, commentId, replyId, httpServletRequest);
    }

    public void deleteReply(Long feedId, Long commentId, Long replyId, HttpServletRequest httpServletRequest) {
        replyCommandService.deleteReply(feedId, commentId, replyId, httpServletRequest);
    }
}