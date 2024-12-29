package com.hys;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hys.runGroup.mapper")
public class RunGroupApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunGroupApplication.class,args);
    }

}
