package _2024.winter.demopico.domain.image.dto;

import lombok.Data;

@Data
public class ImageUploadSuccessRequest {
    private String imageName;
    private String imageUrl;
}

