package com.scores.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	private final String description = "API service for user score management";
	private final String version = "1.0.0";
	private final String email = "contact_support@dogood.com";
	private final String name = "Dogood";

	@Bean
	public OpenAPI swaggerConfiguration() {

		return new OpenAPI()
			.info(new Info()
					  .title(description)
					  .version(version)
					  .contact(new Contact().email(email).name(name)));
	}

}