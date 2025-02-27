package _2024.winter.demopico.domain.feed.repository;

import _2024.winter.demopico.domain.feed.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedCustomRepository {
    void updateFeed (Feed oldFeed, String title, String content, String thumbnail, String plainText, String hashtag);

    Page<Feed> searchFeeds(String search, Pageable pageable);
    Page<Feed> hashtagFeeds(String hashtag, Pageable pageable);
    Page<Feed> searchAndHashtagFeeds(String search, String hashtag, Pageable pageable);
}
