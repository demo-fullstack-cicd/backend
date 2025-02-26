package _2024.winter.demopico.domain.comment.repository;

import _2024.winter.demopico.domain.comment.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentCustomRepository {
    void updateComment(Comment oldComment, String newContent);

}
