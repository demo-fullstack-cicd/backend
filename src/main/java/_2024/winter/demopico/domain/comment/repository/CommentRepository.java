package _2024.winter.demopico.domain.comment.repository;

import _2024.winter.demopico.domain.comment.entity.Comment;
import _2024.winter.demopico.domain.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
    List<Comment> findAllByFeed(Feed feed);
}
