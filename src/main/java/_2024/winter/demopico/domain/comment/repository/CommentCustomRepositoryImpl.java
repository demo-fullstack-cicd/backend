package _2024.winter.demopico.domain.comment.repository;

import _2024.winter.demopico.domain.comment.entity.Comment;
import _2024.winter.demopico.domain.comment.entity.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional
    public void updateComment(Comment oldComment, String content) {
        QComment comment = QComment.comment;

        jpaQueryFactory.update(comment)
                .set(comment.content, content)
                .where(comment.eq(oldComment))
                .execute();
    }
}
