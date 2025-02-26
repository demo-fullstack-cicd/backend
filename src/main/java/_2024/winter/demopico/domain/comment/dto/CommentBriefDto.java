package _2024.winter.demopico.domain.comment.dto;

import _2024.winter.demopico.domain.reply.dto.ReplyBriefDto;
import _2024.winter.demopico.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentBriefDto {
    public String id;
    public String content;
    public String uploadAt;
    public String author;
    public List<ReplyBriefDto> replies = new ArrayList<>();

    public CommentBriefDto(Comment comment){
        this.id = comment.getId().toString();
        this.content = comment.getContent();
        this.uploadAt = comment.getUploadAt().toString();
        this.author = comment.getUser().getUsername();
        this.replies = comment.getReplies().stream().map(ReplyBriefDto::new).toList();
    }
}
