package com.cybersoft.capstone.payload.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AcceptedResponse<T> extends BaseResponse<T> {
    private static final int code = HttpStatus.ACCEPTED.value();
    private static final String message = HttpStatus.ACCEPTED.getReasonPhrase();
    private static final Void data = null;

    public AcceptedResponse() {
        super(code, message);
    }

}
