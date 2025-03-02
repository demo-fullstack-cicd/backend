package _2024.winter.demopico.domain.study.repository;

import _2024.winter.demopico.domain.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long>, StudyCustomRepository {
    List<Study> findByJoinYearAndJoinSemester(int year, int semester);

}
