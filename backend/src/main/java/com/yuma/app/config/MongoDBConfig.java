package com.yuma.app.config;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.document.Consumer;
import com.yuma.app.repository.ConsumersRepository;

@Slf4j
@EnableMongoRepositories(basePackageClasses = ConsumersRepository.class)
@Configuration
public class MongoDBConfig {
	
	private Logger logger = LoggerFactory.getLogger("db logger");
	

	@Bean
	CommandLineRunner commandLineRunner(ConsumersRepository consumersRepository) {
		logger.info("this is logging stuff into the DB");
		return strings -> {
			consumersRepository.save(new Consumer(UUID.randomUUID(), "Ahmad", "baiazid"));
			consumersRepository.save(new Consumer(UUID.randomUUID(), "Nate", "Lego"));
		};
	}
}
