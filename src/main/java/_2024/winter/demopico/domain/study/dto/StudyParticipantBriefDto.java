package _2024.winter.demopico.domain.study.dto;

import _2024.winter.demopico.domain.study.entity.StudyParticipant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyParticipantBriefDto {
    private String username;

    public StudyParticipantBriefDto(StudyParticipant studyParticipant){
        this.username = studyParticipant.getUser().getUsername();
    }
}
