package _2024.winter.demopico.domain.feed.dto.response;

import _2024.winter.demopico.domain.feed.dto.FeedBriefDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetFeedsResponse {
    public int page;
    public int size;
    public int totalElements;
    List<FeedBriefDto> feeds;


}
