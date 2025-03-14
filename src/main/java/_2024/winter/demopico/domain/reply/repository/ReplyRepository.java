package _2024.winter.demopico.domain.reply.repository;

import _2024.winter.demopico.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyCustomRepository {
}
