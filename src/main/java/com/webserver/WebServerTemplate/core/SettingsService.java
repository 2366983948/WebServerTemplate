package com.webserver.WebServerTemplate.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
public class SettingsService {

    private static final String CONFIG_FILE_PATH = "config/settings.json";
    private final ObjectMapper objectMapper;


    public SettingsService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // 检查文件是否存在，如果不存在则创建并写入默认值
    public SettingsEntity checkAndCreateSettingsFile() throws IOException {
        File configFile = new File(CONFIG_FILE_PATH);

        // 检查并创建目录
        File parentDir = configFile.getParentFile();
        if (!parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("目录已创建：" + parentDir.getAbsolutePath());
            } else {
                System.out.println("目录创建失败：" + parentDir.getAbsolutePath());
                return null; // 或者抛出异常，根据你的需求
            }
        }
        if (!configFile.exists()) {
            // 文件不存在，创建并初始化默认值
            SettingsEntity defaultSettingsEntity = getDefaultSettings();
            objectMapper.writeValue(configFile, defaultSettingsEntity);
            System.out.println("配置文件不存在，已创建默认设置文件：" + CONFIG_FILE_PATH);
            return defaultSettingsEntity;
        } else {
            // 文件存在，读取配置
            return objectMapper.readValue(configFile, SettingsEntity.class);
        }
    }

    // 获取默认设置
    private SettingsEntity getDefaultSettings() {
        SettingsEntity settingsEntity = new SettingsEntity();
        SettingsEntity.Database database = new SettingsEntity.Database();
        database.setInitialize(false);
        settingsEntity.setDatabase(database);

        SettingsEntity.Admin admin = new SettingsEntity.Admin();
        admin.setUsername("admin");
        admin.setPassword("123456");
        settingsEntity.setAdmin(admin);

        return settingsEntity;
    }

    // 更新配置文件
    public void updateSettings(SettingsEntity settingsEntity) throws IOException {
        File configFile = new File(CONFIG_FILE_PATH);
        objectMapper.writeValue(configFile, settingsEntity);
        System.out.println("配置文件已更新：" + CONFIG_FILE_PATH);
    }
}
