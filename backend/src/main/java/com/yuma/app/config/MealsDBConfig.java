//package com.yuma.app.config;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import com.yuma.app.document.Ingredients;
//import com.yuma.app.document.Meal;
//import com.yuma.app.repository.MealRepository;
//
//@Slf4j
//@EnableMongoRepositories(basePackageClasses = MealRepository.class)
//@Configuration
//public class MealsDBConfig {
//
//	private Logger logger = LoggerFactory.getLogger("meal logger");
//
//
//	List<Ingredients> ingredientsList = new ArrayList<>();
//	Ingredients ingredient1 = new Ingredients("tomato", 123.343, 0.002, 59.00);
//	Ingredients ingredient2 = new Ingredients("potato", 3.343, 0.02, 58.00);
//	Ingredients ingredient3 = new Ingredients("shomato", 13.343, 0.2, 57.00);
//	Ingredients ingredient4 = new Ingredients("shamesho", 1.343, 0.000234, 5.00);
//
//
//	@Bean
//	CommandLineRunner commandLineRunner(MealRepository mealRepository) {
//		logger.info("this is logging meals into the DB");
//		
//		ingredientsList.add(ingredient1);
//		ingredientsList.add(ingredient2);
//		ingredientsList.add(ingredient3);
//		ingredientsList.add(ingredient4);
//		
//		return strings -> {
//			mealRepository.save(new Meal(UUID.randomUUID(), "chicken anshit", true,ingredientsList));
//			mealRepository.save(new Meal(UUID.randomUUID(), "Beef nshiet", true, ingredientsList));
//
//		};
//	}
//}
