package org.dmitry.example.logleveldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogLevelDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogLevelDemoApplication.class, args);
	}
}
