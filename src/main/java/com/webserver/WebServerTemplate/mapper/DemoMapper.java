package com.webserver.WebServerTemplate.mapper;

import com.webserver.WebServerTemplate.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DemoMapper {
    @Select("select * from demo")
    List<User> GetAllUser();
}
