package com.updatedrms.rate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
		info = @Info(
				title = "Rate Management System in Logistic Domain",
				version = "1.0"
		)
)

@SpringBootApplication

public class RateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateApplication.class, args);
	}

}
