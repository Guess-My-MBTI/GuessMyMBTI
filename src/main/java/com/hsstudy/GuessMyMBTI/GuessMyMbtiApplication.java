package com.hsstudy.GuessMyMBTI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // datasource 설정이 없는 경우 해당 어노테이션 사용하면 정상 기동됨
public class GuessMyMbtiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuessMyMbtiApplication.class, args);
	}

}
