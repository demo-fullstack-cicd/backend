package _2024.winter.demopico.domain.reply.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.CommentException;
import _2024.winter.demopico.common.apiPayload.failure.customException.FeedException;
import _2024.winter.demopico.common.apiPayload.failure.customException.ReplyException;
import _2024.winter.demopico.common.apiPayload.failure.customException.UserException;
import _2024.winter.demopico.domain.reply.dto.request.UpdateReplyRequest;
import _2024.winter.demopico.domain.reply.dto.request.UploadReplyRequest;
import _2024.winter.demopico.domain.reply.dto.response.DeleteReplyResponse;
import _2024.winter.demopico.domain.reply.dto.response.UpdateReplyResponse;
import _2024.winter.demopico.domain.reply.dto.response.UploadReplyResponse;
import _2024.winter.demopico.domain.comment.entity.Comment;
import _2024.winter.demopico.domain.comment.repository.CommentRepository;
import _2024.winter.demopico.domain.reply.entity.Reply;
import _2024.winter.demopico.domain.reply.repository.ReplyRepository;
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
public class ReplyCommandService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final ReplyRepository replyRepository;
    private final JWTUtil jwtUtil;

    public UploadReplyResponse uploadReply(UploadReplyRequest request, Long feedId, Long commentId, HttpServletRequest httpServletRequest){
        log.info("[ReplyCommandService - uploadReply]");

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentException.CommentNotExistException::new);

        Reply reply  = Reply.builder()
                .content(request.getContent())
                .uploadAt(LocalDateTime.now())
                .feed(feed)
                .user(user)
                .comment(comment)
                .build();

        replyRepository.saveAndFlush(reply);

        return UploadReplyResponse.builder()
                .replyId(reply.getId())
                .build();

    }

    public UpdateReplyResponse updateReply(UpdateReplyRequest request, Long feedId, Long commentId, Long replyId, HttpServletRequest httpServletRequest){
        log.info("[ReplyCommandService - updateReply]");

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentException.CommentNotExistException::new);

        Reply reply = replyRepository.findById(replyId).orElseThrow(ReplyException.ReplyNotExistException::new);

        validationOwnershipComment(reply, user, feed, comment);

        replyRepository.updateReply(reply, request.getContent());

        replyRepository.saveAndFlush(reply);

        return UpdateReplyResponse.builder()
                .replyId(reply.getId())
                .build();
    }

    public void deleteReply(Long feedId, Long commentId, Long replyId, HttpServletRequest httpServletRequest){
        log.info("[ReplyCommandService - deleteReply]");

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentException.CommentNotExistException::new);


        Reply reply = replyRepository.findById(replyId).orElseThrow(ReplyException.ReplyNotExistException::new);


        validationOwnershipComment(reply, user, feed, comment);

        replyRepository.delete(reply);
    }

    private void validationOwnershipComment(Reply reply, User user, Feed feed, Comment comment){
        if (!reply.getUser().equals(user) || !reply.getFeed().equals(feed) || !reply.getComment().equals(comment)){
            throw new ReplyException.ReplyNotOwnershipException();
        }
    }

}