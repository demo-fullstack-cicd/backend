package _2024.winter.demopico.domain.study.repository;

import _2024.winter.demopico.domain.study.entity.Study;
import _2024.winter.demopico.domain.study.entity.StudyParticipant;
import _2024.winter.demopico.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyParticipantRepository extends JpaRepository<StudyParticipant, Long>{
    List<StudyParticipant> findAllByStudy(Study study);
    List<StudyParticipant> findAllByUser(User user);
    void deleteByStudy(Study study);
    boolean existsByStudyAndUser(Study study, User user);

}
