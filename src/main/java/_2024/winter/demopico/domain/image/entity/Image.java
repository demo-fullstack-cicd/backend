package _2024.winter.demopico.domain.image.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imageName")
    private String imageName;

    @Column(name = "uploadedAt")
    private LocalDateTime  uploadedAt;

    @Builder
    public Image(String imageName, LocalDateTime uploadedAt) {
        this.imageName = imageName;
        this.uploadedAt = uploadedAt;
    }
}
