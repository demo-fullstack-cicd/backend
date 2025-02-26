package _2024.winter.demopico.domain.feed.repository;

import _2024.winter.demopico.domain.feed.entity.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedCustomRepository {
    void updateFeed (Feed oldFeed, String title, String content, String thumbnail, String plainText);

    Page<Feed> searchFeeds(String search, Pageable pageable);
}
