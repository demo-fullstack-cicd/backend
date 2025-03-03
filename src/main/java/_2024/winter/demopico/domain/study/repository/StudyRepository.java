package _2024.winter.demopico.domain.study.repository;

import _2024.winter.demopico.domain.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long>, StudyCustomRepository {
    List<Study> findByJoinYearAndJoinSemester(int year, int semester);

}
