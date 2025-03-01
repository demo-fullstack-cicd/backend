package _2024.winter.demopico.domain.feed.dto;

import _2024.winter.demopico.domain.feed.entity.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedBriefDto {
    private String id;
    private String title;
    private String uploadAt;
    private String author;
    private String thumbnail;
    private String commentSize;


    public FeedBriefDto(Feed feed){
        this.id = feed.getId().toString();
        this.title = feed.getTitle();
        this.author = feed.getUser().getUsername();
        this.uploadAt = feed.getUploadAt().toString();
        this.thumbnail = feed.getThumbnail();
        this.commentSize = String.valueOf(feed.getComments().size());
    }
}
