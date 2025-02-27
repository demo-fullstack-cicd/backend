package _2024.winter.demopico.domain.feed.repository;

import _2024.winter.demopico.domain.feed.entity.Feed;
import _2024.winter.demopico.domain.feed.entity.QFeed;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FeedCustomRepositoryImpl implements FeedCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional
    public void updateFeed(Feed oldFeed, String title, String content, String thumbnail, String plainText, String hashtag) {
        QFeed feed = QFeed.feed;

        jpaQueryFactory.update(feed)
                .set(feed.title, title)
                .set(feed.content, content)
                .set(feed.thumbnail, thumbnail)
                .set(feed.plainText, plainText)
                .set(feed.hashtag, hashtag)
                .where(feed.eq(oldFeed))
                .execute();
    }

    @Override
    public Page<Feed> searchFeeds(String search, Pageable pageable) {
        QFeed feed = QFeed.feed;
        BooleanExpression likesSearch = feed.title.likeIgnoreCase("%" + search + "%")
                .or(feed.plainText.likeIgnoreCase("%" + search + "%"));

        List<Feed> feeds = jpaQueryFactory.selectFrom(feed)
                .where(likesSearch)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(
                        feed.uploadAt.desc()
                )
                .fetch();

        long total = Optional.ofNullable(jpaQueryFactory.select(feed.count())
                .from(feed)
                .where(likesSearch)
                .fetchOne()).orElse(0L);

        return new PageImpl<>(feeds, pageable, total);
    }

    @Override
    public Page<Feed> hashtagFeeds(String hashtag, Pageable pageable) {
        QFeed feed = QFeed.feed;

        List<Feed> feeds = jpaQueryFactory.selectFrom(feed)
                .where(feed.hashtag.eq(hashtag))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(feed.uploadAt.desc())
                .fetch();

        long total = Optional.ofNullable(jpaQueryFactory.select(feed.count())
                .from(feed)
                .where(feed.hashtag.eq(hashtag))
                .fetchOne()).orElse(0L);

        return new PageImpl<>(feeds, pageable, total);
    }


    @Override
    public Page<Feed> searchAndHashtagFeeds(String search, String hashtag, Pageable pageable) {
        QFeed feed = QFeed.feed;
        BooleanExpression likesSearch = feed.title.likeIgnoreCase("%" + search + "%")
                .or(feed.plainText.likeIgnoreCase("%" + search + "%"));

        List<Feed> feeds = jpaQueryFactory.selectFrom(feed)
                .where(likesSearch.and(
                        feed.hashtag.eq(hashtag)
                ))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(
                        feed.uploadAt.desc()
                )
                .fetch();

        long total = Optional.ofNullable(jpaQueryFactory.select(feed.count())
                .from(feed)
                .where(likesSearch.and(
                        feed.hashtag.eq(hashtag)
                ))
                .fetchOne()).orElse(0L);

        return new PageImpl<>(feeds, pageable, total);
    }
}
