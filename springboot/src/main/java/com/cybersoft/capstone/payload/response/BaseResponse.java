package com.cybersoft.capstone.payload.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class BaseResponse<T> {
    private final int code;
    private final String message;

    @Setter
    private T data;
}
