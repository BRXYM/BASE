package zjb.jtdx.qx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileResourceConfiguration implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(FileResourceConfiguration.class);
    private String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "uploads";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path absolutePath = Paths.get(desktopPath).toAbsolutePath();
        if (Files.exists(absolutePath) && Files.isDirectory(absolutePath)) {
            logger.info("静态资源文件夹: {}", absolutePath.toString());
            registry.addResourceHandler("/files/**")
                    .addResourceLocations("file:" + absolutePath + "/");
        } else {
            logger.error("静态资源文件夹不存在: {}", absolutePath.toString());
        }
    }
}
