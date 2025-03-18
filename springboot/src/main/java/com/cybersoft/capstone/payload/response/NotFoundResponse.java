package com.cybersoft.capstone.payload.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundResponse<ErrMessage> extends BaseResponse<ErrMessage> {
    private static final int code = HttpStatus.NOT_FOUND.value();
    private static final String message = HttpStatus.NOT_FOUND.getReasonPhrase();

    public NotFoundResponse(ErrMessage data) {
        super(code, message);
        setData(data);
    }
}
