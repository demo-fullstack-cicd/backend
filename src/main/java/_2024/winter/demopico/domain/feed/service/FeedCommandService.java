package _2024.winter.demopico.domain.feed.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.FeedException;
import _2024.winter.demopico.common.apiPayload.failure.customException.UserException;
import _2024.winter.demopico.domain.feed.dto.request.UpdateOneFeedRequest;
import _2024.winter.demopico.domain.feed.dto.request.UploadFeedRequest;
import _2024.winter.demopico.domain.feed.dto.response.DeleteOneFeedResponse;
import _2024.winter.demopico.domain.feed.dto.response.UpdateOneFeedResponse;
import _2024.winter.demopico.domain.feed.dto.response.UploadOneFeedResponse;
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
public class FeedCommandService {

    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public UploadOneFeedResponse uploadFeed(UploadFeedRequest request, HttpServletRequest httpServletRequest){
        log.info("[FeedCommandService - uploadFeed]");

        String username = jwtUtil.getUsername(httpServletRequest.getHeader("access"));
        User user = userRepository.findByUsername(username).orElseThrow(UserException.UsernameNotExistException::new);

        Feed feed = Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .uploadAt(LocalDateTime.now())
                .user(user)
                .thumbnail(request.getThumbnail())
                .plainText(request.getPlainText())
                .build();

        feedRepository.saveAndFlush(feed);

        return UploadOneFeedResponse.builder()
                .feedId(feed.getId())
                .build();

    }


    public DeleteOneFeedResponse deleteOneFeed(Long feedId){
        log.info("[FeedCommandService - deleteOneFeed]");

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        feedRepository.delete(feed);

        return DeleteOneFeedResponse.builder().build();
    }


    public UpdateOneFeedResponse updateOneFeed(Long feedId, UpdateOneFeedRequest request){
        log.info("[FeedCommandService - updateOneFeed]");

        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        feedRepository.updateFeed(feed, request.getTitle(), request.getContent(), request.getThumbnail(), request.getPlainText());

        return UpdateOneFeedResponse.builder().build();
    }
}
