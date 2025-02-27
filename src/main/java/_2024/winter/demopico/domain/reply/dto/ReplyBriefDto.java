package _2024.winter.demopico.domain.reply.dto;

import _2024.winter.demopico.domain.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyBriefDto {
    public String id;
    public String content;
    public String uploadAt;
    public String author;

    public ReplyBriefDto(Reply reply){
        this.id = reply.getId().toString();
        this.content = reply.getContent();
        this.uploadAt = reply.getUploadAt().toString();
        this.author = reply.getUser().getUsername();
    }
}

