package _2024.winter.demopico.domain.reply.repository;

import _2024.winter.demopico.domain.reply.entity.Reply;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyCustomRepository {
    void updateReply(Reply oldReply, String content);
}
