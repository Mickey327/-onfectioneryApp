package com.example.confectioneryApp;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
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
