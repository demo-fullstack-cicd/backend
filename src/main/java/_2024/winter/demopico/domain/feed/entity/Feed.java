package _2024.winter.demopico.domain.feed.entity;

import _2024.winter.demopico.domain.comment.entity.Comment;
import _2024.winter.demopico.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feeds")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob  // 긴 문자열 저장
    @Column(name = "content", columnDefinition = "TEXT")  // TEXT 타입으로 지정
    private String content;


    @Column(name = "uploadAt")
    private LocalDateTime uploadAt;

    @Lob  // 긴 문자열 저장
    @Column(name = "thumbnail", columnDefinition = "TEXT")
    private String thumbnail;

    @Column(name="plainText")
    private String plainText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "hashtag")
    private String hashtag;

    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();



    @Builder

    public Feed(String title, String content, LocalDateTime uploadAt, User user, String thumbnail, String plainText, String hashtag) {
        this.title = title;
        this.content = content;
        this.uploadAt = uploadAt;
        this.user = user;
        this.thumbnail = thumbnail;
        this.plainText = plainText;
        this.hashtag = hashtag;
    }
}
