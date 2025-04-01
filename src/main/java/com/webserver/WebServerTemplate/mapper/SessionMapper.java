package com.webserver.WebServerTemplate.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SessionMapper {
    @Select("select sessionId from session where uid=#{uid}")
    Integer GetSession(int uid);

    @Update("update session set sessionId=#{sessionId} where uid=#{uid}")
    int SetSession(int uid ,int sessionId);

    @Insert("insert into session values(#{uid},#{sessionId})")
    int InsertSession(int uid ,int sessionId);
}
