package _2024.winter.demopico.common.apiPayload.failure.customExceptionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StudyExceptionStatus {

    STUDY_NOT_EXIST(HttpStatus.BAD_REQUEST, "40600", "존재하지 않는 스터디그룹 입니다."),
    STUDY_NOT_OWNER(HttpStatus.BAD_REQUEST, "40601", "스터디그룹 정보를 수정, 삭제할 권한이 없습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

