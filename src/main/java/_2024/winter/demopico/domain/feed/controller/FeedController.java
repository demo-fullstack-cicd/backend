package _2024.winter.demopico.domain.feed.controller;

import _2024.winter.demopico.common.apiPayload.success.SuccessApiResponse;
import _2024.winter.demopico.domain.feed.dto.request.UpdateOneFeedRequest;
import _2024.winter.demopico.domain.feed.dto.request.UploadFeedRequest;
import _2024.winter.demopico.domain.feed.dto.response.*;
import _2024.winter.demopico.domain.feed.service.FeedApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class FeedController {
    private final FeedApplicationService feedApplicationService;

    // 게시글 페이징
    @GetMapping("/feeds")
    public SuccessApiResponse<GetFeedsResponse> getFeeds(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String hashtag)
    {
        log.info("[FeedController - getFeeds] page = {}, size = {}, search = {}, hashtag = {}", page, size, search, hashtag);
        return SuccessApiResponse.onSuccessGetFeeds(feedApplicationService.getFeeds(page, size, search, hashtag));
    }

    // 게시글 업로드
    @PostMapping("/feed")
    public SuccessApiResponse<UploadOneFeedResponse> uploadOneFeed(
            @RequestBody UploadFeedRequest request,
            HttpServletRequest httpServletRequest)
    {
        log.info("[FeedController - uploadFeed] uploadFeedRequest = {}", request);
        return SuccessApiResponse.onSuccessUploadOneFeed(feedApplicationService.uploadOneFeed(request, httpServletRequest));
    }

    // 게시글 세부 조회
    @GetMapping("/feeds/{feedId}")
    public SuccessApiResponse<GetOneFeedResponse> getOneFeed(
            @PathVariable("feedId") Long feedId)
    {
        log.info("[FeedController - getOneFeed] feedId = {}", feedId);
        return SuccessApiResponse.onSuccessGetOneFeed(feedApplicationService.getOneFeed(feedId));
    }

    // 게시글 삭제
    @DeleteMapping("/feeds/{feedId}")
    public SuccessApiResponse<DeleteOneFeedResponse> deleteOneFeed(
            @PathVariable("feedId") Long feedId)
    {
        log.info("[FeedController - deleteOneFeed] feedId = {}", feedId);
        return SuccessApiResponse.onSuccessDeleteOneFeed(feedApplicationService.deleteOneFeed(feedId));
    }

    // 게시글 수정
    @PatchMapping("/feeds/{feedId}")
    public SuccessApiResponse<UpdateOneFeedResponse> updateOneFeed(
            @PathVariable("feedId") Long feedId,
            @RequestBody UpdateOneFeedRequest request)
    {
        log.info("[FeedController - uploadOneFeed] feedId = {}", feedId);
        return SuccessApiResponse.onSuccessUpdateOneFeed(feedApplicationService.updateOneFeed(feedId, request));
    }

}
