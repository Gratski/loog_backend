package com.dalbot.ai.cmcopilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.dalbot.ai.cmcopilot.repository"})
public class ContentManagementCoPilotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentManagementCoPilotApplication.class, args);
	}

}
