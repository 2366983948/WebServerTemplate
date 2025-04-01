package com.webserver.WebServerTemplate.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.webserver.WebServerTemplate.core.SessionSystem;
import com.webserver.WebServerTemplate.entity.User;
import com.webserver.WebServerTemplate.mapper.DemoMapper;
import com.webserver.WebServerTemplate.utils.JWT;
import com.webserver.WebServerTemplate.utils.WebResponse.BaseResponse;
import com.webserver.WebServerTemplate.utils.WebResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {
    @Autowired
    DemoMapper demoMapper;
    @GetMapping("/get")
    public List<User> GetAllUser(){
        return demoMapper.GetAllUser();
    }
    @GetMapping("/getSession")
    public Integer GetSession(int uid){
        return SessionSystem.getSession(uid);
    }
    @PostMapping("/setSession")
    public String SetSession(int uid,int sid){
        SessionSystem.setSession(uid,sid);
        return "完成";
    }
    @PostMapping("/login")
    public Response<Map<String ,Object>> Login(@RequestBody JsonNode jsonNode){
        try {
            Map<String ,Object>resultNode = new HashMap<>();
            if(jsonNode.has("username") && jsonNode.has("password")){
                String username = jsonNode.get("username").asText();
                String password = jsonNode.get("password").asText();

                String TOKEN = JWT.GenerateToken(123456);
                resultNode.put("TOKEN",TOKEN);
                resultNode.put("userInfo","用户信息");
                return Response.OK("登陆成功",resultNode);
            }else{
                throw new Exception("没有提供账号");
            }
        }catch (Exception e){
//            System.err.println(e);
            return Response.Error(e.getMessage());
        }
    }
    @GetMapping("/TestToken")
    public BaseResponse TestToken(){
        return Response.OK_NoData("测试通过");
    }
}
