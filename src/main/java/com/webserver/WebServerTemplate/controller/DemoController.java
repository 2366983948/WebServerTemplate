package com.webserver.WebServerTemplate.controller;

import com.webserver.WebServerTemplate.entity.User;
import com.webserver.WebServerTemplate.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    DemoMapper demoMapper;
    @GetMapping("/get")
    public List<User> GetAllUser(){
        return demoMapper.GetAllUser();
    }
}
