package com.webserver.WebServerTemplate.utils.WebResponse;

public class BaseResponse {
    public int code = ResponseCode.ERROR.getCode();
    public String message = "[no message]";

    BaseResponse(){};
    BaseResponse(int code, String message){
        this.code = code;
        this.message = message;
    }
}
