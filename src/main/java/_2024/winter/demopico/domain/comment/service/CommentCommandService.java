package _2024.winter.demopico.domain.comment.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.CommentException;
import _2024.winter.demopico.common.apiPayload.failure.customException.FeedException;
import _2024.winter.demopico.common.apiPayload.failure.customException.UserException;
import _2024.winter.demopico.domain.comment.dto.request.UpdateCommentRequest;
import _2024.winter.demopico.domain.comment.dto.request.UploadCommentRequest;
import _2024.winter.demopico.domain.comment.dto.response.DeleteCommentResponse;
import _2024.winter.demopico.domain.comment.dto.response.UpdateCommentResponse;
import _2024.winter.demopico.domain.comment.dto.response.UploadCommentResponse;
import _2024.winter.demopico.domain.comment.entity.Comment;
import _2024.winter.demopico.domain.comment.repository.CommentRepository;
import _2024.winter.demopico.domain.feed.entity.Feed;
import _2024.winter.demopico.domain.feed.repository.FeedRepository;
import _2024.winter.demopico.domain.user.entity.User;
import _2024.winter.demopico.domain.user.repository.UserRepository;
import _2024.winter.demopico.domain.user.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final JWTUtil jwtUtil;

    public UploadCommentResponse uploadComment(UploadCommentRequest request, Long feedId, HttpServletRequest httpServletRequest){
        log.info("[CommentCommandService - uploadComment]");

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        Comment comment = Comment.builder()
                .content(request.getContent())
                .uploadAt(LocalDateTime.now())
                .feed(feed)
                .user(user)
                .build();

        commentRepository.saveAndFlush(comment);

        return UploadCommentResponse.builder()
                .commentId(comment.getId())
                .build();

    }

    public UpdateCommentResponse updateComment(UpdateCommentRequest request, Long feedId, Long commentId, HttpServletRequest httpServletRequest){
        log.info("[CommentCommandService - updateComment]");

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentException.CommentNotExistException::new);

        validationOwnershipComment(comment, user, feed);

        commentRepository.updateComment(comment, request.getContent());

        commentRepository.saveAndFlush(comment);

        return UpdateCommentResponse.builder()
                .commentId(comment.getId())
                .build();
    }

    public void deleteComment(Long feedId, Long commentId, HttpServletRequest httpServletRequest){
        log.info("[CommentCommandService - deleteComment]");

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentException.CommentNotExistException::new);

        validationOwnershipComment(comment, user, feed);

        commentRepository.delete(comment);
    }


    private void validationOwnershipComment(Comment comment, User user, Feed feed){
        if (!comment.getUser().equals(user) || !comment.getFeed().equals(feed)){
            throw new CommentException.CommentNotOwnershipException();
        }
    }
}
