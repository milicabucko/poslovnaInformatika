package com.poslovna.fakturisanje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WebAppInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebAppInitializer.class, args);
	}
}
