package _2024.winter.demopico.domain.image.dto.response;

import _2024.winter.demopico.domain.image.dto.ImageInfoDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetImagesResponse {
    public List<ImageInfoDto> images;
}
