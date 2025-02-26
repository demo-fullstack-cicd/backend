package _2024.winter.demopico.common.apiPayload.failure.customExceptionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ImageExceptionStatus {

    IMAGE_NOT_EXIST(HttpStatus.BAD_REQUEST, "40100", "존재하지 않는 이미지입니다."),
    IMAGE_DUPLICATE(HttpStatus.BAD_REQUEST, "40101", "이미 존재하는 이미지입니다.")


    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

