package com.test.soccerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SoccerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerApiApplication.class, args);
	}


}
