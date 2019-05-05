package com.hfut.pw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hfut.pw.dao")
public class PwApplication {

	public static void main(String[] args) {
		SpringApplication.run(PwApplication.class, args);
	}

}
