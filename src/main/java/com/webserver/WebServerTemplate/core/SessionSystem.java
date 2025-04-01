package com.webserver.WebServerTemplate.core;

import com.webserver.WebServerTemplate.mapper.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/** 用户会话系统
 * 用户会话表，key:uid ； value:sessionId
 */
@Component
public class SessionSystem {
    /**
     * 使用块表机制，优先读取sessionTable中有没有Session，如果没有再去数据库中寻找，找到的记录再块表中
     * 如果数据库中没有，要创建。
     *
     * 有关Session过期问题：Session的过期时间和Token一致，每次重新生成Token的时候也会生成新的Session
     */
    private static final int MAX_SIZE = 5;  // 表的最大长度
    private static Map<Integer,Integer> sessionTable = new HashMap<>(MAX_SIZE);
    private static Queue<Integer>queue = new LinkedList<Integer>();
    private static SessionMapper sessionMapper;

    @Autowired
    SessionSystem(SessionMapper _sessionMapper){
        sessionMapper = _sessionMapper;
    }
    public static void setSession(int key, int value) {
        if(sessionTable.size() >= MAX_SIZE && !sessionTable.containsKey(key)){
            try{
                Remove16();
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        sessionTable.put(key,value);
        queue.add(key);
    }

    public static int getSession(int key) {
        if(sessionTable.containsKey(key)){
            return sessionTable.get(key);
        }
        Integer session = sessionMapper.GetSession(key);
        if(session != null){
            setSession(key,session);
            return session;
        }
        return -1;
    }

    public static int GenerateSession(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
        String timeString = now.format(formatter);
        return Integer.parseInt(timeString);
    }
    // 移除16个元素,移除的数据要放入数据库
    private static void Remove16() throws Exception{
        Integer earliestKey;
        for(int i =0;i<1;i++){
            earliestKey = queue.poll(); // 获取并删除队列头部元素
            if (earliestKey != null) {
                int val = sessionTable.get(earliestKey);
                int result = sessionMapper.SetSession(earliestKey,val);
                if(result == 0){
                    result = sessionMapper.InsertSession(earliestKey,val);
                    if(result == 0)throw new Exception("Session数据库插入失败");
                }
                sessionTable.remove(earliestKey);
            }else {
                return;
            }
        }
    }
}
