package com.cybersoft.capstone.payload.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class BadRequestResponse<ErrMessage> extends BaseResponse<ErrMessage> {
    private static final int code = HttpStatus.BAD_REQUEST.value();
    private static final String message = HttpStatus.BAD_REQUEST.getReasonPhrase();

    public BadRequestResponse(ErrMessage errMessage) {
        super(code, message);
        setData(errMessage);
    }
}
