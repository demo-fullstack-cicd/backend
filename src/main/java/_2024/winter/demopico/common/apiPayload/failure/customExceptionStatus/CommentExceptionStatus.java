package _2024.winter.demopico.common.apiPayload.failure.customExceptionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommentExceptionStatus {

    COMMENT_NOT_EXIST(HttpStatus.BAD_REQUEST, "40300", "존재하지 않는 코멘트입니다."),
    COMMENT_NOT_OWNERSHIP(HttpStatus.BAD_REQUEST, "40301", "본인이 작성하지 않은 코멘트입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
