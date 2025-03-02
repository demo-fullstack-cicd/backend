package _2024.winter.demopico.domain.study.dto.request;

import _2024.winter.demopico.domain.study.entity.StudyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudyRequest {
    private String title;
    private String description;
    private int capacity;
    private int currentParticipants;
    private String location;
    private String organizer;
    private int joinYear;
    private int joinSemester;
    private StudyStatus status;
    private List<String> participants;
}