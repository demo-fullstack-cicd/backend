package _2024.winter.demopico.domain.image.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageInfoDto {
    private Long imageId;
    private String imageName;
    private LocalDateTime uploadAt;

}
