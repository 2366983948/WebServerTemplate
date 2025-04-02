package com.webserver.WebServerTemplate;

import com.webserver.WebServerTemplate.core.SettingsEntity;
import com.webserver.WebServerTemplate.core.SettingsService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@MapperScan("com.webserver.WebServerTemplate.mapper")
@SpringBootApplication
public class WebServerTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServerTemplateApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx, SettingsService settingsService) {
		return args -> {
			// 检查并读取设置文件
			SettingsEntity settingsEntity = settingsService.checkAndCreateSettingsFile();
			if(settingsEntity == null){
				System.err.println("未能读取到配置文件");
				return;
			}
			System.out.println("当前数据库初始化状态：" + settingsEntity.getDatabase().isInitialize());
			System.out.println("管理员用户名：" + settingsEntity.getAdmin().getUsername());

			// 修改配置并写回文件
			if(!settingsEntity.getDatabase().isInitialize()){
				System.out.println("数据库初始化");
				settingsEntity.getDatabase().setInitialize(true);
			}
			settingsService.updateSettings(settingsEntity);
		};
	}
}
