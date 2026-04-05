package com.ritz.weekly_meals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeeklyMealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeeklyMealsApplication.class, args);
	}

}
