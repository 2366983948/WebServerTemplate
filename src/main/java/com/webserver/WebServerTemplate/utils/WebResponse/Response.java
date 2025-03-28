package com.webserver.WebServerTemplate.utils.WebResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Response<T> extends BaseResponse {
    private T data = null;

    // 默认构造方法
    public Response() {}

    // 构造方法
    public Response(int code, String message) {
        super(code, message);
    }

    public Response(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    // 静态工厂方法
    public static <T> Response<T> OK(String message) {
        return new Response<>(ResponseCode.OK.getCode(), message);
    }
    public static <T> Response<T> OK(String message,T data) {
        return new Response<>(ResponseCode.OK.getCode(), message, data);
    }
    public static <T> Response<T> Error() {
        return new Response<>(ResponseCode.ERROR.getCode(), "[ERROR]:请求失败");
    }
    public static <T> Response<T> Error(String msg) {
        return new Response<>(ResponseCode.ERROR.getCode(), "[ERROR]:"+msg);
    }
    public static <T> Response<T> Error(String message,T data) {
        return new Response<>(ResponseCode.ERROR.getCode(), message, data);
    }
    public static <T> Response<T> Error(ResponseCode code,String msg) {
        return new Response<>(code.getCode(), "[ERROR]:"+msg);
    }
    public static <T> Response<T> Error(ResponseCode code,String message,T data) {
        return new Response<>(code.getCode(), message, data);
    }
    public static BaseResponse OK_NoData(String message) {
        return new Response<>(ResponseCode.OK.getCode(), message);
    }
    public static  BaseResponse Error_NoData() {
        return new Response<>(ResponseCode.ERROR.getCode(), "[ERROR]:请求失败");
    }
    public static  BaseResponse Error_NoData(String msg) {
        return new Response<>(ResponseCode.ERROR.getCode(), "[ERROR]:"+msg);
    }
    public static  BaseResponse Error_NoData(ResponseCode code,String msg) {
        return new Response<>(code.getCode(), "[ERROR]:"+msg);
    }
    // toString方法
    @Override
    public String toString() {
        return "Response{" +
                "code=" + super.code +
                ", message='" + super.code + '\'' +
                ", data=" + data +
                '}';
    }

}