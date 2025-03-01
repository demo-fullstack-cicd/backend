package _2024.winter.demopico.domain.user.repository;

import _2024.winter.demopico.domain.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomRepository {
    void updateBio(User oldUser, String bio);
}
