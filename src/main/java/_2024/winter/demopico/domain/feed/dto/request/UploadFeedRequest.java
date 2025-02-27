package _2024.winter.demopico.domain.feed.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UploadFeedRequest {
    public String title;
    public String content;
    public String thumbnail;
    public String plainText;
    public String hashtag;
}
