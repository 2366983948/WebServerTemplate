package com.webserver.WebServerTemplate.utils.WebResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BaseResponse {
    public int code = ResponseCode.ERROR.getCode();
    public String message = "[no message]";

    BaseResponse(){}
    BaseResponse(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("code",code);
        json.put("message",message);
        return json.toString();
    }
}
