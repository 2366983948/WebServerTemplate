package com.webserver.WebServerTemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.webserver.WebServerTemplate.mapper")
@SpringBootApplication
public class WebServerTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServerTemplateApplication.class, args);
	}

}
