package com.mailorderpharmacy.refillservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients
public class RefillMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefillMicroServiceApplication.class, args);
	}

}
