package _2024.winter.demopico.domain.comment.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.FeedException;
import _2024.winter.demopico.domain.comment.dto.CommentBriefDto;
import _2024.winter.demopico.domain.comment.dto.response.GetCommentsResponse;
import _2024.winter.demopico.domain.comment.entity.Comment;
import _2024.winter.demopico.domain.comment.repository.CommentRepository;
import _2024.winter.demopico.domain.feed.entity.Feed;
import _2024.winter.demopico.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;


    public GetCommentsResponse getComments(Long feedId){
        log.info("[CommentQueryService - getComments]");

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        List<Comment> comments = commentRepository.findAllByFeed(feed);

        List<CommentBriefDto> commentBriefDtos = comments.stream()
                .map(CommentBriefDto::new)
                .toList();

        return GetCommentsResponse.builder()
                .totalElements(commentBriefDtos.size())
                .comments(commentBriefDtos)
                .build();


    }

}
