package com.webserver.WebServerTemplate.utils;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseHelper {
    String jdbcUrl;
    String username;
    String password;
    String sqlFilePath = "sql/init.sql";

    public DatabaseHelper(){
        Properties properties = new Properties();
        try (InputStream input = DatabaseHelper.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Sorry, unable to find application.properties");
            return;
        }
        // 获取值
        jdbcUrl = properties.getProperty("spring.datasource.url");
        username = properties.getProperty("spring.datasource.username");
        password = properties.getProperty("spring.datasource.password");
    }
    public Boolean ExecuteFile(){
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            ClassPathResource resource = new ClassPathResource(sqlFilePath);
            InputStreamReader reader = new InputStreamReader(resource.getInputStream(),"UTF-8");

            ScriptRunner scriptRunner = new ScriptRunner(conn);
            scriptRunner.setLogWriter(null); // 关闭日志输出
            scriptRunner.runScript(reader);

            System.out.println("SQL 文件执行完成！");
            return true;
        } catch (Exception e) {
            System.err.println("SQL 文件执行失败！");
            System.err.println(e);
            return false;
        }
    }
}
