package com.cybersoft.capstone.payload.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OkResponse<T> extends BaseResponse<T> {
    private static final int code = HttpStatus.OK.value();
    private static final String message = HttpStatus.OK.getReasonPhrase();

    public OkResponse(T data) {
        super(code, message);
        setData(data);
    }

}
