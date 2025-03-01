package _2024.winter.demopico.domain.feed.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOneFeedResponse {
    private String id;
    private String title;
    private String uploadAt;
    private String content;
    private String author;
    private String thumbnail;
    private String commentSize;

}
