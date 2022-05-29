package com.example.confectioneryApp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "confectionery-app",
				version = "1.0.0",
				description = "Documentation for app",
				contact = @Contact(
						name = "Ulanov Pavel",
						email = "usp2002@mail.ru"
				)
		)
)
public class ConfectioneryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfectioneryAppApplication.class, args);
	}

	@Bean
	@Profile("test")
	public FlywayMigrationStrategy cleanMigrateStrategy() {
		FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
			@Override
			public void migrate(Flyway flyway) {
				flyway.clean();
				flyway.migrate();
			}
		};

		return strategy;
	}
}
