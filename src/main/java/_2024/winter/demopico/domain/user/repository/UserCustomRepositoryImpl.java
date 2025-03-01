package _2024.winter.demopico.domain.user.repository;

import _2024.winter.demopico.domain.user.entity.QUser;
import _2024.winter.demopico.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional
    public void updateBio(User oldUser, String bio) {
        QUser user = QUser.user;

        jpaQueryFactory.update(user)
                .set(user.bio,  bio)
                .where(user.eq(oldUser))
                .execute();

    }
}
