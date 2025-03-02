package _2024.winter.demopico.domain.study.repository;

import _2024.winter.demopico.domain.study.dto.request.UpdateStudyRequest;
import _2024.winter.demopico.domain.study.entity.Study;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCustomRepository {

    void updateStudy(Study oldStudy, UpdateStudyRequest request);
}
