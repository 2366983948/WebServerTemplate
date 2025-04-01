package com.webserver.WebServerTemplate.controller;

import com.webserver.WebServerTemplate.core.SessionSystem;
import com.webserver.WebServerTemplate.entity.User;
import com.webserver.WebServerTemplate.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

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
}
