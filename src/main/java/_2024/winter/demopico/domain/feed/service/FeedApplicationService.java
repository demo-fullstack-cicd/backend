package _2024.winter.demopico.domain.feed.service;

import _2024.winter.demopico.domain.feed.dto.request.UpdateOneFeedRequest;
import _2024.winter.demopico.domain.feed.dto.request.UploadFeedRequest;
import _2024.winter.demopico.domain.feed.dto.response.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedApplicationService {
    private final FeedCommandService feedCommandService;
    private final FeedQueryService feedQueryService;

    public GetFeedsResponse getFeeds(int page, int size, String search, String hashtag){
        return feedQueryService.getFeeds(page, size, search, hashtag);
    }

    public UploadOneFeedResponse uploadOneFeed(UploadFeedRequest request, HttpServletRequest httpServletRequest){
        return feedCommandService.uploadFeed(request, httpServletRequest);
    }

    public GetOneFeedResponse getOneFeed(Long feedId){
        return feedQueryService.getOneFeed(feedId);
    }

    public DeleteOneFeedResponse deleteOneFeed(Long feedId){
        return feedCommandService.deleteOneFeed(feedId);
    }

    public UpdateOneFeedResponse updateOneFeed(Long feedId, UpdateOneFeedRequest request){
        return feedCommandService.updateOneFeed(feedId, request);
    }

}
