package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.test.example.mapper")
public class SpringBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBaseApplication.class, args);
    }

}
