package zjb.jtdx;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("zjb.jtdx.qx.mapper")
public class QxApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(QxApplication.class, args);
    }
}
