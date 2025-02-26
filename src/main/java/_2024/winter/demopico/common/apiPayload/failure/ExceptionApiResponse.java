package _2024.winter.demopico.common.apiPayload.failure;

import _2024.winter.demopico.common.apiPayload.BaseApiResponse;
import lombok.Getter;

@Getter
public class ExceptionApiResponse extends BaseApiResponse {

    public ExceptionApiResponse(Boolean isSuccess, String code, String message) {
        super(isSuccess, code, message);
    }

}
