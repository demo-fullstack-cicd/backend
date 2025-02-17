package _2024.winter.demopico.domain.image.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetImageResponse {
    public String imageName;
    public LocalDateTime uploadAt;

    @Builder
    public GetImageResponse(String imageName,LocalDateTime uploadAt) {
        this.imageName = imageName;
        this.uploadAt = uploadAt;
    }
}
