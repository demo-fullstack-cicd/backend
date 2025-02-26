package _2024.winter.demopico.domain.feed.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.FeedException;
import _2024.winter.demopico.domain.feed.dto.FeedBriefDto;
import _2024.winter.demopico.domain.feed.dto.response.GetFeedsResponse;
import _2024.winter.demopico.domain.feed.dto.response.GetOneFeedResponse;
import _2024.winter.demopico.domain.feed.entity.Feed;
import _2024.winter.demopico.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedQueryService {

    private final FeedRepository feedRepository;

    public GetFeedsResponse getFeeds(int page, int size, String search) {
        log.info("[FeedQueryService - getFeeds]");

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "uploadAt"));
        Page<Feed> pagingFeeds = (search == null || search.trim().isEmpty())
                ? feedRepository.findAll(pageable)
                : feedRepository.searchFeeds(search, pageable);

        List<FeedBriefDto> pagingBriefFeeds = pagingFeeds.getContent().stream()
                .map(FeedBriefDto::new)
                .toList();

        return GetFeedsResponse.builder()
                .page(page)
                .size(size)
                .totalElements((int) pagingFeeds.getTotalElements())
                .feeds(pagingBriefFeeds)
                .build();
    }

    public GetOneFeedResponse getOneFeed(Long feedId){
        log.info("[FeedQueryService - getOneFeed]");
        Feed feed = feedRepository.findById(feedId).orElseThrow(FeedException.FeedNotExistException::new);

        return GetOneFeedResponse.builder()
                .id(feed.getId().toString())
                .title(feed.getTitle())
                .content(feed.getContent())
                .uploadAt(feed.getUploadAt().toString())
                .author(feed.getUser().getUsername())
                .thumbnail(feed.getThumbnail())
                .build();
    }
}
