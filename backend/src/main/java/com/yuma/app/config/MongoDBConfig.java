package com.yuma.app.config;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.document.Consumer;
import com.yuma.app.repository.ConsumersRepository;


@EnableMongoRepositories(basePackageClasses = ConsumersRepository.class)
@Configuration
public class MongoDBConfig {

	@Bean
	CommandLineRunner commandLineRunner(ConsumersRepository consumersRepository) {
		return strings -> {
			consumersRepository.save(new Consumer(UUID.randomUUID(), "Ahmad", "baiazid"));
			consumersRepository.save(new Consumer(UUID.randomUUID(), "Nate", "Lego"));
		};
	}
}
