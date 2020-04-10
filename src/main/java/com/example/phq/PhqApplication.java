package com.example.phq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = {"com.example.phq.mapper"})
@SpringBootApplication
public class PhqApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhqApplication.class, args);
    }

}
