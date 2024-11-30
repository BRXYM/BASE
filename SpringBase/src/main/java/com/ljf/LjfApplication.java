package com.ljf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ljf.dts.mapper")
public class LjfApplication {
    public static void main(String[] args) {
        SpringApplication.run(LjfApplication.class, args);
    }
}
