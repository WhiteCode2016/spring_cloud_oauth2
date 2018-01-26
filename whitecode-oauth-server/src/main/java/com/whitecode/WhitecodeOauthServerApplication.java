package com.whitecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class WhitecodeOauthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhitecodeOauthServerApplication.class, args);
	}
}
