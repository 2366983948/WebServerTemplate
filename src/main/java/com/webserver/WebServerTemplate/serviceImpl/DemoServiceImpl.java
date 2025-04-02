package com.webserver.WebServerTemplate.serviceImpl;

import com.webserver.WebServerTemplate.entity.User;
import com.webserver.WebServerTemplate.mapper.DemoMapper;
import com.webserver.WebServerTemplate.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    DemoMapper demoMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> GetAllUser() {
        return demoMapper.GetAllUser();
    }
}
