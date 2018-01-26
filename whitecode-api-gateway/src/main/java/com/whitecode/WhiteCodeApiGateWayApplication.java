package com.whitecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@EnableOAuth2Sso
@SpringBootApplication
public class WhiteCodeApiGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteCodeApiGateWayApplication.class, args);
	}
}
