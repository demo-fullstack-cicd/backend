package _2024.winter.demopico.domain.user.dto.response;

import _2024.winter.demopico.domain.feed.dto.FeedBriefDto;
import _2024.winter.demopico.domain.study.dto.StudyBriefDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetInfoResponse {
    private String username;
    private String email;
    private String studentId;
    private String phone;
    private String name;
    private String bio;

    @Builder.Default
    private List<FeedBriefDto> feeds = new ArrayList<>();
    @Builder.Default
    private List<StudyBriefDto> studies = new ArrayList<>();
}
