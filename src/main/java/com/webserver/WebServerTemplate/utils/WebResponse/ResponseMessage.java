package com.webserver.WebServerTemplate.utils.WebResponse;

import java.util.Map;

public class ResponseMessage {
    private static final Map<ResponseCode, String> messageMap = Map.of(
            ResponseCode.OK,"成功",
            ResponseCode.ERROR,"失败",
            ResponseCode.NoToken,"请求头不完整，请在请求头中添加TOKEN和uid",
            ResponseCode.TokenExpired,"Token 已过期，请重新申请",
            ResponseCode.IllegalAccess,"非法访问：Token不属于该用户",
            ResponseCode.NoSession,"没有创建会话，请尝试登录",
            ResponseCode.SessionExpired,"会话终止：账号在其他设备登录"
    );

    public static Map<ResponseCode, String> getMessageMap() {
        return messageMap;
    }
}
