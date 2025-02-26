package _2024.winter.demopico.domain.comment.service;

import _2024.winter.demopico.domain.comment.dto.request.UpdateCommentRequest;
import _2024.winter.demopico.domain.comment.dto.request.UploadCommentRequest;
import _2024.winter.demopico.domain.comment.dto.response.DeleteCommentResponse;
import _2024.winter.demopico.domain.comment.dto.response.GetCommentsResponse;
import _2024.winter.demopico.domain.comment.dto.response.UpdateCommentResponse;
import _2024.winter.demopico.domain.comment.dto.response.UploadCommentResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentApplicationService {
    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    public UploadCommentResponse uploadComment(UploadCommentRequest request, Long feedId, HttpServletRequest httpServletRequest){
        return commentCommandService.uploadComment(request, feedId, httpServletRequest);
    }

    public UpdateCommentResponse updateComment(UpdateCommentRequest request, Long feedId, Long commentId, HttpServletRequest httpServletRequest){
        return commentCommandService.updateComment(request, feedId, commentId, httpServletRequest);
    }

    public DeleteCommentResponse deleteComment(Long feedId, Long commentId, HttpServletRequest httpServletRequest){
        return commentCommandService.deleteComment(feedId, commentId, httpServletRequest);
    }

    public GetCommentsResponse getComments(Long feedId){
        return commentQueryService.getComments(feedId);
    }


}
