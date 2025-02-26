package _2024.winter.demopico.common.apiPayload.failure.customExceptionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FeedExceptionStatus {

    FEED_NOT_EXIST(HttpStatus.BAD_REQUEST, "40200", "존재하지 않는 피드입니다."),


    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
