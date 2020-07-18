package com.fzq.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
//@ComponentScan(basePackages = { "com.fzq.test"})
public class SbtwithmybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbtwithmybatisApplication.class, args);
	}

}
