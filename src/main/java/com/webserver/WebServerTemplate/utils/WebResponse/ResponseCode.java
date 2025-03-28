package com.webserver.WebServerTemplate.utils.WebResponse;

public enum ResponseCode {
    OK(20000),
    ERROR(20001);

    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}