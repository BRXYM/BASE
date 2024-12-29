package com.hys.runGroup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class FileResourceConfiguration implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(FileResourceConfiguration.class);

    // 获取桌面路径
    private String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "uploads";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 确保路径格式正确
        Path absolutePath = Paths.get(desktopPath).toAbsolutePath();

        // 检查路径是否存在并可读
        if (Files.exists(absolutePath) && Files.isDirectory(absolutePath)) {
            logger.info("静态资源文件夹: {}", absolutePath.toString());
            registry.addResourceHandler("/files/**")
                    .addResourceLocations("file:" + absolutePath + "/");
        } else {
            logger.error("静态资源文件夹不存在: {}", absolutePath.toString());
        }
    }
}
