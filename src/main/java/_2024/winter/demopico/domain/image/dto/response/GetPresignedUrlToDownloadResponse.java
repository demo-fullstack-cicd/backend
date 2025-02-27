package _2024.winter.demopico.domain.image.dto.response;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPresignedUrlToDownloadResponse {
    public String presignedUrl;
}
