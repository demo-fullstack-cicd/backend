package _2024.winter.demopico.domain.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DownloadPresignedUrlDto {
    public String presignedUrl;
}
