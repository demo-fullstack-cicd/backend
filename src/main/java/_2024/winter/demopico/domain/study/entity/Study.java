package _2024.winter.demopico.domain.study.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "studies")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "currentParticipants")
    private int currentParticipants;

    @Column(name = "location")
    private String location;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "joinYear")
    private int joinYear;

    @Column(name = "joinSemester")
    private int joinSemester;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    @Column(name = "document")
    private String document;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyParticipant> studyParticipants = new ArrayList<>();

    @Builder
    public Study(StudyStatus status, int joinSemester, int joinYear, String organizer, String location, int currentParticipants, int capacity, String description, String title, String document) {
        this.status = status;
        this.joinSemester = joinSemester;
        this.joinYear = joinYear;
        this.organizer = organizer;
        this.location = location;
        this.currentParticipants = currentParticipants;
        this.capacity = capacity;
        this.description = description;
        this.title = title;
        this.document = document;
    }
}
