package _2024.winter.demopico.domain.study.dto.response;

import _2024.winter.demopico.domain.study.dto.StudyBriefDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetStudiesResponse {
    private List<StudyBriefDto> studies;
}
