package _2024.winter.demopico.domain.reply.entity;
import _2024.winter.demopico.domain.comment.entity.Comment;
import _2024.winter.demopico.domain.feed.entity.Feed;
import _2024.winter.demopico.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "replys")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "uploadAt")
    private LocalDateTime uploadAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public Reply(String content, LocalDateTime uploadAt, User user, Feed feed, Comment comment) {
        this.content = content;
        this.uploadAt = uploadAt;
        this.user = user;
        this.feed = feed;
        this.comment = comment;
    }
}