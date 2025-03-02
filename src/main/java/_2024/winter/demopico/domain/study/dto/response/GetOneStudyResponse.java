package _2024.winter.demopico.domain.study.dto.response;

import _2024.winter.demopico.domain.study.dto.StudyParticipantBriefDto;
import _2024.winter.demopico.domain.study.entity.StudyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetOneStudyResponse {
    private Long id;
    private String title;
    private String description;
    private int capacity;
    private int currentParticipants;
    private String location;
    private String organizer;
    private int joinYear;
    private int joinSemester;
    private StudyStatus status;
    private List<StudyParticipantBriefDto> studyParticipants;
    private String document;
    private String schedule;
}
