package com.roerdev.pruebaapiassert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PruebaApiAssertApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApiAssertApplication.class, args);
	}

}
