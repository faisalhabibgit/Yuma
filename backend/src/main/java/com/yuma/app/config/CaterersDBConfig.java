package com.yuma.app.config;


import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.repository.CatererRepository;

@Slf4j
@EnableMongoRepositories(basePackageClasses = CatererRepository.class)

@Configuration
public class CaterersDBConfig {

//	List<Ingredients> ingredientsList = new ArrayList<>();
//	Ingredients ingredient1 = new Ingredients("tomato", 123.343, 0.002, 59.00);
//	Ingredients ingredient2 = new Ingredients("potato", 3.343, 0.02, 58.00);
//	Ingredients ingredient3 = new Ingredients("shomato", 13.343, 0.2, 57.00);
//	Ingredients ingredient4 = new Ingredients("shamesho", 1.343, 0.000234, 5.00);
//	private Logger logger = LoggerFactory.getLogger("caterer logger");
//
//	@Bean
//	CommandLineRunner commandLineRunner1(CatererRepository catererRepository) {
//		logger.info("this is logging caterer into the DB");
//		return strings -> {
//			catererRepository.save(new Caterer(UUID.randomUUID(), "OrelMoisa", "orelmoisa@gmail.com", new Address(5601, "smart", "CSL", "QC", "Canada", "H4W2m4"), "chinese", new ArrayList<>(), "234"));
//			catererRepository.save(new Caterer(UUID.randomUUID(), "JohnSmith", "johnsmith@gmail.com", new Address(1601, "clever", "CSL", "QC", "Canada", "H4L7I8"), "greek", new ArrayList<>(), "20190807"));
//
//
//		};
//	}
//
//	@Bean
//	CommandLineRunner commandLineRunner2(MealRepository mealRepository) {
//		logger.info("this is logging meals into the DB");
//
//		ingredientsList.add(ingredient1);
//		ingredientsList.add(ingredient2);
//		ingredientsList.add(ingredient3);
//		ingredientsList.add(ingredient4);
//
//		return strings -> {
//			mealRepository.save(new Meal(ingredientsList, UUID.randomUUID(), "chicken anshit", true, new HashSet<>()));
//			mealRepository.save(new Meal(ingredientsList, UUID.randomUUID(), "Beef nshiet", true, new HashSet<>()));
//
//		};
//	}
//
//	@Bean
//	CommandLineRunner commandLineRunner3(ConsumersRepository consumersRepository) {
//		logger.info("this is logging stuff into the DB");
//		return strings -> {
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Ahmad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, new HashSet<>()), "20190807"));
//
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Nate", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, new HashSet<>()), "20190807"));
//		};
//	}
}
