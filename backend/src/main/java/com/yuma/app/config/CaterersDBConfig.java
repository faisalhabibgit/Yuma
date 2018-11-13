package com.yuma.app.config;


import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.repository.CatererRepository;

@Slf4j
@EnableMongoRepositories(basePackageClasses = CatererRepository.class)

@Configuration
public class CaterersDBConfig {

//	@Bean
//	CommandLineRunner generateData (MealRepository mealRepository, ConsumersRepository consumersRepository){
//		ArrayList<Ingredients> ingredientsList = new ArrayList<Ingredients>();
//		//bogus ingredients
//		Ingredients ingredient1 = new Ingredients("tomato", 123.343, 0.002, 59.00);
//		Ingredients ingredient2 = new Ingredients("potato", 3.343, 0.02, 58.00);
//		Ingredients ingredient3 = new Ingredients("shomato", 13.343, 0.2, 57.00);
//		Ingredients ingredient4 = new Ingredients("shamesho", 1.343, 0.000234, 5.00);
//		ingredientsList.add(ingredient1);
//		ingredientsList.add(ingredient2);
//		ingredientsList.add(ingredient3);
//		ingredientsList.add(ingredient4);
//
//		HashSet<String> allAllergies = new HashSet<>();
//		allAllergies.add("dairy");
//		allAllergies.add("peanuts");
//		allAllergies.add("gluten");
//		allAllergies.add("shellfish");
//		allAllergies.add("soy");
//
//		HashSet<String> dairy = new HashSet<>();
//		dairy.add("dairy");
//
//		HashSet<String> nuts = new HashSet<>();
//		nuts.add("peanuts");
//		nuts.add("Tree Nuts");
//
//		HashSet<String> shellfish = new HashSet<>();
//		shellfish.add("shellfish");
//
//		HashSet<String> soy = new HashSet<>();
//		soy.add("soy");
//
//
//		return strings -> {
//			mealRepository.save(new Meal(ingredientsList, UUID.randomUUID(), "dangerous chicken", true, allAllergies));
//			mealRepository.save(new Meal(ingredientsList, UUID.randomUUID(), "milky beef", true, dairy));
//			mealRepository.save(new Meal(ingredientsList, UUID.randomUUID(), "nutty soup", true, nuts));
//			mealRepository.save(new Meal(ingredientsList, UUID.randomUUID(), "fish", true, shellfish));
//			mealRepository.save(new Meal(ingredientsList, UUID.randomUUID(), "dishMadeWithSoy", true, soy));
//
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Ahmad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Nate", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "AhmadsBrother", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "gio1", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "gio2", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "gio3", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "gio4", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "gio5", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "gio6", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "dad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, dairy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "mom", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, dairy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Brother", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, dairy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "sister", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, dairy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "sister1", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, dairy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "sister2", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, dairy), "20190807"));
//
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Ahmad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, nuts), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Ahmad4", "baiazid7", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, nuts), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Nate", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, nuts), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "AhmadsBrother", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, nuts), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "gio", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, nuts), "20190807"));
//
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "dad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, soy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "mom", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, soy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Brother", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, soy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "sister", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, soy), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "sister", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, soy), "20190807"));
//
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin1", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin2", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin3", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin4", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin5", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin6", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin7", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin8", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin9", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousin10", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, shellfish), "20190807"));
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "cousi", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//		};
//
//	}
}

//	List<Ingredients> ingredientsList = new ArrayList<>();
//Ingredients ingredient2 = new Ingredients("potato", 3.343, 0.02, 58.00);
////	Ingredients ingredient3 = new Ingredients("shomato", 13.343, 0.2, 57.00);
////	Ingredients ingredient4 = new Ingredients("shamesho", 1.343, 0.000234, 5.00);
////	private Logger logger = LoggerFactory.getLogger("caterer logger");
////	Ingredients ingredient1 = new Ingredients("tomato", 123.343, 0.002, 59.00);
//	
//	@Bean
//	CommandLineRunner commandLineRunner1(CatererRepository catererRepository) {
//		logger.info("this is logging caterer into the DB");
//		return strings -> {
//			catererRepository.save(new Caterer(UUID.randomUUID(), "OrelMoisa", "orelmoisa@gmail.com", new Address(5601, "smart", "CSL", "QC", "Canada", "H4W2m4"), "chinese", new ArrayList<>(), "234"));
//			catererRepository.save(new Caterer(UUID.randomUUID(), "JohnSmith", "johnsmith@gmail.com", new Address(1601, "clever", "CSL", "QC", "Canada", "H4L7I8"), "greek", new ArrayList<>(), "20190807"));

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
//			consumersRepository.save(new Consumer(UUID.randomUUID(), "Nate", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, new HashSet<>()), "20190807"));
//		};
//	}
//}
