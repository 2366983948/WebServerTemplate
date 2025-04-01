package com.webserver.WebServerTemplate.utils.WebResponse;

public enum ResponseCode {
    OK(20000),
    ERROR(20001),
    NoToken(20002),//没有token或uid
    TokenExpired(20003),//token过期
    IllegalAccess(20004),//token被篡改
    NoSession(20005),//没有会话id
    SessionExpired(20006),//session过期

    UnKnownError(40000);
    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}