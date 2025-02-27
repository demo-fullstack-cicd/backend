package _2024.winter.demopico.domain.reply.repository;

import _2024.winter.demopico.domain.reply.entity.QReply;
import _2024.winter.demopico.domain.reply.entity.Reply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReplyCustomRepositoryImpl implements ReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional
    public void updateReply(Reply oldReply, String content) {
        QReply reply = QReply.reply;

        jpaQueryFactory.update(reply)
                .set(reply.content, content)
                .where(reply.eq(oldReply))
                .execute();
    }

}
