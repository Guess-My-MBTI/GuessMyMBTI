package com.hsstudy.GuessMyMBTI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class GuessMyMbtiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuessMyMbtiApplication.class, args);
    }
}

