package com.meditrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.meditrack")
@EnableJpaRepositories
public class MeditrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeditrackApplication.class, args);
	}

}
