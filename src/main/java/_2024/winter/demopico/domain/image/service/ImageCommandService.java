package _2024.winter.demopico.domain.image.service;

import _2024.winter.demopico.common.apiPayload.failure.customException.ImageException;
import _2024.winter.demopico.domain.image.dto.request.ImageUploadRequest;
import _2024.winter.demopico.domain.image.dto.response.UploadImageResponse;
import _2024.winter.demopico.domain.image.entity.Image;
import _2024.winter.demopico.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ImageCommandService {
    private final ImageRepository imageRepository;

    public UploadImageResponse uploadImage(ImageUploadRequest request) {

        if (imageRepository.existsByImageName(request.getImageName())){
            throw new ImageException.ImageDuplicateException();
        }

        Image image = Image.builder()
                .imageName(request.getImageName())
                .uploadedAt(LocalDateTime.now())
                .build();

        imageRepository.saveAndFlush(image);

        return UploadImageResponse.builder()
                .imageId(image.getId())
                .build();
    }
}
