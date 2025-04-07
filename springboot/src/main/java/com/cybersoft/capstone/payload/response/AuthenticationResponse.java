package com.cybersoft.capstone.payload.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private int code;
    private String message;
    private Object data;
}
