package _2024.winter.demopico.domain.comment.entity;

import _2024.winter.demopico.domain.feed.entity.Feed;
import _2024.winter.demopico.domain.reply.entity.Reply;
import _2024.winter.demopico.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
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

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Comment(String content, LocalDateTime uploadAt, User user, Feed feed) {
        this.content = content;
        this.uploadAt = uploadAt;
        this.user = user;
        this.feed = feed;
    }
}
