package _2024.winter.demopico.domain.image.repository;

import _2024.winter.demopico.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageName(String imageName);
    boolean existsByImageName(String imageName);
}
