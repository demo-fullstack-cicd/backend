package _2024.winter.demopico.domain.study.dto;

import _2024.winter.demopico.domain.study.entity.Study;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyBriefDto {
    private String id;
    private String title;
    private String description;
    private String organizer;
    private int capacity;
    private int currentParticipants;

    public StudyBriefDto(Study study){
        this.id = study.getId().toString();
        this.title = study.getTitle();
        this.description = study.getDescription();
        this.organizer = study.getOrganizer();
        this.capacity = study.getCapacity();
        this.currentParticipants = study.getCurrentParticipants();
    }
}
