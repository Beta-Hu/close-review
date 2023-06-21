package com.beta.closereview;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(value = "com.beta.closereview.mapper")
public class CloseReviewApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloseReviewApplication.class, args);
    }
}
