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
    public String id;
    public String title;
    public String uploadAt;
    public String content;
    public String author;
    public String thumbnail;

}
