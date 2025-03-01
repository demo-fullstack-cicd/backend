package _2024.winter.demopico.domain.feed.repository;

import _2024.winter.demopico.domain.feed.entity.Feed;
import _2024.winter.demopico.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long>, FeedCustomRepository {
    List<Feed> findByUser(User user);
}
