package com.yuma.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.repository.MealRepository;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Preferences;
import com.yuma.app.repository.ConsumersRepository;

@Slf4j
@EnableMongoRepositories(basePackageClasses = ConsumersRepository.class)
@Configuration
public class MongoDBConfig {

	private Logger logger = LoggerFactory.getLogger("db logger");


	@Bean
	CommandLineRunner commandLineRunner(ConsumersRepository consumersRepository, MealRepository mealRepository) {
		logger.info("this is logging stuff into the DB");
		return strings -> {
			consumersRepository.save(new Consumer(UUID.randomUUID(), "Ahmad", "baiazid", "ahmad.lego@gmail.com","ahmad.lego@work.com", new Preferences(2, true, false, new HashMap<>()), "20190807"));

			consumersRepository.save(new Consumer(UUID.randomUUID(), "Nate", "Lego", "nate.lego@gmail.com","nate.lego@work.com", new Preferences(2, true, false, new HashMap< >()),"20190807"));
			mealRepository.save(new Meal(UUID.randomUUID(), "mutton", true, new ArrayList<Ingredients>(){
				{
					add(new Ingredients("tomatoes", 30, 30, 30));
				}
			}));

			mealRepository.save(new Meal(UUID.randomUUID(), "chineese", true, new ArrayList<Ingredients>(){
				{
					add(new Ingredients("peppers", 20, 20, 20));
				}
			}));
		};
	}
}
