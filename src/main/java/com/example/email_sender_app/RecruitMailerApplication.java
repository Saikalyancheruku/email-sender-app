package com.example.email_sender_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RecruitMailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitMailerApplication.class, args);
	}
}
