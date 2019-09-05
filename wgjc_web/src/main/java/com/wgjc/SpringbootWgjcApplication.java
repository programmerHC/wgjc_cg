package com.wgjc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.wgjc")
@EnableTransactionManagement
@MapperScan("com.wgjc.*.dao")
public class SpringbootWgjcApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringbootWgjcApplication.class);
	}
}
