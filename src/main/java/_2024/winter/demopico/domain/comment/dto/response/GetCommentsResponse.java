package _2024.winter.demopico.domain.comment.dto.response;

import _2024.winter.demopico.domain.comment.dto.CommentBriefDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentsResponse {
    int totalElements;
    public List<CommentBriefDto> comments;
}
