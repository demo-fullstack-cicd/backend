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
    public String id;
    public String title;
    public String uploadAt;
    public String author;
    public String thumbnail;

    public FeedBriefDto(Feed feed){
        this.id = feed.getId().toString();
        this.title = feed.getTitle();
        this.author = feed.getUser().getUsername();
        this.uploadAt = feed.getUploadAt().toString();
        this.thumbnail = feed.getThumbnail();
    }
}
