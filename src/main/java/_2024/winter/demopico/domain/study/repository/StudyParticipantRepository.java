package _2024.winter.demopico.domain.study.repository;

import _2024.winter.demopico.domain.study.entity.Study;
import _2024.winter.demopico.domain.study.entity.StudyParticipant;
import _2024.winter.demopico.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyParticipantRepository extends JpaRepository<StudyParticipant, Long>{
    List<StudyParticipant> findAllByStudy(Study study);
    void deleteByStudy(Study study);
    boolean existByStudyAndUser(Study study, User user);
}
