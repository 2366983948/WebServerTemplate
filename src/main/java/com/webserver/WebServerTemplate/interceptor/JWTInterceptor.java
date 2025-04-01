package com.webserver.WebServerTemplate.interceptor;

import com.webserver.WebServerTemplate.core.SessionSystem;
import com.webserver.WebServerTemplate.utils.JWT;
import com.webserver.WebServerTemplate.utils.WebResponse.Response;
import com.webserver.WebServerTemplate.utils.WebResponse.ResponseCode;
import com.webserver.WebServerTemplate.utils.WebResponse.ResponseMessage;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    /**
     *  拦截所有请求，检查Token
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截到"+request.getRequestURL());
        String uid = request.getHeader("uid");
        String token = request.getHeader("TOKEN");
        Map<ResponseCode, String> messageMap = ResponseMessage.getMessageMap();
        if(uid==null || token == null){
            TokenError(response,ResponseCode.NoToken,messageMap.get(ResponseCode.NoToken));
            return false;
        }
        Claims claims = JWT.validateJWT(token);
        if(claims == null){
            TokenError(response,ResponseCode.TokenExpired,messageMap.get(ResponseCode.TokenExpired));
            return false;
        }
        if(!uid.equals(claims.getSubject())){
            TokenError(response,ResponseCode.IllegalAccess,messageMap.get(ResponseCode.IllegalAccess));
            return false;
        }
        int sessionResult = SessionSystem.getSession(Integer.parseInt(uid));
        if(sessionResult == -1){
            TokenError(response,ResponseCode.NoSession,messageMap.get(ResponseCode.NoSession));
            return false;
        }
        if(sessionResult != (Integer) claims.get("session")){
            TokenError(response,ResponseCode.SessionExpired,messageMap.get(ResponseCode.SessionExpired));
            return false;
        }
        return true;
    }

    private void TokenError(HttpServletResponse response,ResponseCode code,String msg) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        writer.write(Response.Error_NoData(code,msg).toString());
        writer.flush();
        writer.close();
        response.flushBuffer();
    }
}