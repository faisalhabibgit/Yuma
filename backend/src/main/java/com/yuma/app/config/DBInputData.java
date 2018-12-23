package com.yuma.app.config;


import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.repository.UserRepository;

@Slf4j
@EnableMongoRepositories(basePackageClasses = UserRepository.class)

@Configuration
public class DBInputData {

	//DATA GENERATION STARTS HERE
//	@Bean
//	CommandLineRunner generateData (MealRepository mealRepository, UserRepository userRepository){
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
//			userRepository.save(new User(UUID.randomUUID(), "Ahmad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "Nate", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "AhmadsBrother", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, allAllergies), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "gio", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, allAllergies), "20190807"));
//
//			userRepository.save(new User(UUID.randomUUID(), "dad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, dairy), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "mom", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, dairy), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "Brother", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, dairy), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "sister", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, dairy), "20190807"));
//
//			userRepository.save(new User(UUID.randomUUID(), "Ahmad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, nuts), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "Nate", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, nuts), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "AhmadsBrother", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, nuts), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "gio", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, nuts), "20190807"));
//
//			userRepository.save(new User(UUID.randomUUID(), "dad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, soy), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "mom", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, soy), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "Brother", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, soy), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "sister", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, soy), "20190807"));
//
//			userRepository.save(new User(UUID.randomUUID(), "cousin1", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "cousin2", "Lego", "nate.lego@gmail.com", "nate.lego@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "cousin3", "LegoEat", "na.lego@gmail.com", "nate.lego@wor.com", new Preferences(2, true, false, shellfish), "20190807"));
//			userRepository.save(new User(UUID.randomUUID(), "cousin4", "ego", "natelego@gmail.com", "nate.leg@work.com", new Preferences(2, true, false, shellfish), "20190807"));
//		};
//
//	}
	
		//DATA GENERATION ENDS HERE
}

