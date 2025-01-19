package adm.control;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("adm.control.all.mapper")
public class AdmApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdmApplication.class, args);
    }
}

