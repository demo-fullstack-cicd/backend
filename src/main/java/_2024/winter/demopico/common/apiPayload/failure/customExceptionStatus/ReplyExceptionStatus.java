package _2024.winter.demopico.common.apiPayload.failure.customExceptionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyExceptionStatus {

    REPLY_NOT_EXIST(HttpStatus.BAD_REQUEST, "40400", "존재하지 않는 코멘트입니다."),
    REPLY_NOT_OWNERSHIP(HttpStatus.BAD_REQUEST, "40401", "본인이 작성하지 않은 코멘트입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
