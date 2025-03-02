package _2024.winter.demopico.domain.study.entity;

import _2024.winter.demopico.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "studyParticipants")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudyParticipant {
    @Id
    @Column(name = "studyParticipant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @Column(name = "studyParticipantRole")
    @Enumerated(EnumType.STRING)
    private StudyParticipantRole studyParticipantRole;

    @Builder
    public StudyParticipant(User user, Study study, StudyParticipantRole studyParticipantRole) {
        this.user = user;
        this.study = study;
        this.studyParticipantRole = studyParticipantRole;
    }
}
