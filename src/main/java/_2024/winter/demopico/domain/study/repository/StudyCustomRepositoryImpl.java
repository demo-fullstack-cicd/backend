package _2024.winter.demopico.domain.study.repository;

import _2024.winter.demopico.domain.study.dto.request.UpdateStudyRequest;
import _2024.winter.demopico.domain.study.entity.QStudy;
import _2024.winter.demopico.domain.study.entity.Study;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudyCustomRepositoryImpl implements StudyCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void updateStudy(Study oldStudy, UpdateStudyRequest request) {
        QStudy study = QStudy.study;

        jpaQueryFactory.update(study)
                .set(study.title, request.getTitle())
                .set(study.description, request.getDescription())
                .set(study.capacity, request.getCapacity())
                .set(study.currentParticipants, request.getCurrentParticipants())
                .set(study.location, request.getLocation())
                .set(study.organizer, request.getOrganizer())
                .set(study.joinYear, request.getJoinYear())
                .set(study.joinSemester, request.getJoinSemester())
                .set(study.status, request.getStatus())
                .where(study.eq(oldStudy))
                .execute();

    }
}
