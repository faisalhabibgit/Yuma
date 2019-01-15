//package com.yuma.app.config;
//
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import com.yuma.app.document.Ingredients;
//import com.yuma.app.document.Meal;
//import com.yuma.app.document.Plan;
//import com.yuma.app.document.Role;
//import com.yuma.app.document.User;
//import com.yuma.app.repository.MealRepository;
//import com.yuma.app.repository.RoleRepository;
//import com.yuma.app.repository.UserRepository;
//
//@Slf4j
//@EnableMongoRepositories(basePackageClasses = RoleRepository.class)
//
//@Configuration
//public class DBInputData {
//
//	@Bean
//	CommandLineRunner init(RoleRepository roleRepository) {
//
//		return args -> {
//
//			Optional<Role> adminRole = roleRepository.findByRole("ADMIN");
//			if (!adminRole.isPresent()) {
//				Role newAdminRole = new Role();
//				newAdminRole.setRole("ADMIN");
//				roleRepository.save(newAdminRole);
//			}
//
//			Optional<Role> userRole = roleRepository.findByRole("USER");
//			if (!userRole.isPresent()) {
//				Role newUserRole = new Role();
//				newUserRole.setRole("USER");
//				roleRepository.save(newUserRole);
//			}
//		};
//
//	}
//	
//	@Bean
//	CommandLineRunner generateData (MealRepository mealRepository, UserRepository userRepository){
//		ArrayList<Ingredients> ingredientsList1 = new ArrayList<>();
//		ArrayList<Ingredients> ingredientsList2 = new ArrayList<>();
//		ArrayList<Ingredients> ingredientsList3 = new ArrayList<>();
//		ArrayList<Ingredients> ingredientsList4 = new ArrayList<>();
//		ArrayList<Ingredients> ingredientsList5 = new ArrayList<>();
//		//bogus ingredients
//		Ingredients chickpeas = new Ingredients("chickpeas", 123.343, 0.002, 59.00, false);
//		Ingredients cheese = new Ingredients("cheese", 3.343, 0.02, 58.00, false);
//		Ingredients chicken = new Ingredients("chicken", 13.343, 0.2, 57.00, false);
//		Ingredients brocolie = new Ingredients("brocolie", 1.343, 0.000234, 5.00, false);
//		Ingredients beef = new Ingredients("beef", 1.343, 0.000234, 5.00, false);
//		Ingredients rice = new Ingredients("rice", 1.343, 0.000234, 5.00, false);
//		Ingredients tomato = new Ingredients("tomato", 1.343, 0.000234, 5.00, false);
//		Ingredients raisin = new Ingredients("raisin", 1.343, 0.000234, 5.00, false);
//		Ingredients banana = new Ingredients("banana", 1.343, 0.000234, 5.00, false);
//		Ingredients fish = new Ingredients("fish", 1.343, 0.000234, 5.00, false);
//		Ingredients potato = new Ingredients("potato", 1.343, 0.000234, 5.00, false);
//		Ingredients nuts = new Ingredients("nuts", 1.343, 0.000234, 5.00, false);
//		Ingredients lamb = new Ingredients("lamb", 1.343, 0.000234, 5.00, false);
//		Ingredients nutsOptional = new Ingredients("nuts", 1.343, 0.000234, 5.00, true);
//		Ingredients onionOptional = new Ingredients("onion", 1.343, 0.000234, 5.00, true);
//		Ingredients chickpeasOptional = new Ingredients("chickpeas", 1.343, 0.000234, 5.00, true);
//		Ingredients pork = new Ingredients("pork", 1.343, 0.000234, 5.00, true);
//		Ingredients milk = new Ingredients("milk", 1.343, 0.000234, 5.00, false);
//
//
//		ingredientsList1.add(chickpeas);
//		ingredientsList1.add(cheese);
//		ingredientsList1.add(chicken);
//		ingredientsList1.add(brocolie);
//
//		ingredientsList2.add(beef);
//		ingredientsList2.add(rice);
//		ingredientsList2.add(tomato);
//		ingredientsList2.add(raisin);
//		ingredientsList2.add(banana);
//
//		ingredientsList3.add(fish);
//		ingredientsList3.add(rice);
//		ingredientsList3.add(potato);
//		ingredientsList3.add(nuts);
//
//		ingredientsList4.add(lamb);
//		ingredientsList4.add(nutsOptional);
//		ingredientsList4.add(onionOptional);
//		ingredientsList4.add(chickpeasOptional);
//		ingredientsList4.add(banana);
//
//		ingredientsList5.add(pork);
//		ingredientsList5.add(potato);
//		ingredientsList5.add(milk);
//		ingredientsList5.add(chicken);
//
//
//		List<String> dislikes1 = new ArrayList<>();
//		List<String> dislikes2 = new ArrayList<>();
//		List<String> dislikes3 = new ArrayList<>();
//		List<String> dislikes4 = new ArrayList<>();
//
//		String dislikeOnion = "onion";
//		String dislikenuts = "nuts";
//		String disliketomato = "tomato";
//
//		dislikes1.add(dislikeOnion);
//		dislikes1.add(dislikenuts);
//
//		dislikes2.add(disliketomato);
//
//		dislikes4.add(dislikeOnion);
//
//		Plan plan1 = new Plan(4,false,false, new HashSet<>());
//		Plan plan2 = new Plan(2,false,false, new HashSet<>());
//		Plan plan3 = new Plan(7,false,false, new HashSet<>());
//		Plan plan4 = new Plan(5,false,false, new HashSet<>());
//
//		User user1 = new User(UUID.randomUUID().toString(),"ahmad", "baiazid", "ahmad.baiazid@hotmail.com", "pass1", true, plan1,dislikes1);
//		User user2 = new User(UUID.randomUUID().toString(),"fais", "habib", "fais.habibi@hotmail.com", "pass2", true,plan2, dislikes2);
//		User user3 = new User(UUID.randomUUID().toString(),"nate", "nathy", "nate.nathy@hotmail.com", "pass3", true,plan3,  dislikes3);
//		User user4 = new User(UUID.randomUUID().toString(),"john", "doe", "john.doe@hotmail.com", "pass4", true, plan4, dislikes4);
//
//
//		Meal meal1 = new Meal(UUID.randomUUID(), "chick broc", "chicken and broccolie topped with a touch of love", true, new HashSet<>(), ingredientsList1);
//		Meal meal2 = new Meal(UUID.randomUUID(), "beef and rice", "beef and rice topped with a touch of love", true, new HashSet<>(), ingredientsList2);
//		Meal meal3 = new Meal(UUID.randomUUID(), "fish and rice", "fish and rice topped with a touch of love", true, new HashSet<>(), ingredientsList3);
//		Meal meal4 = new Meal(UUID.randomUUID(), "lamb", "lamb topped with a touch of love", true, new HashSet<>(),ingredientsList4);
//		Meal meal5 = new Meal(UUID.randomUUID(), "pork", "topped with a touch of love", true, new HashSet<>(), ingredientsList5);
//		Meal meal6 = new Meal(UUID.randomUUID(), "fish and rice", "fish and rice topped with a touch of love", false, new HashSet<>(), ingredientsList2);
//		Meal meal7 = new Meal(UUID.randomUUID(), "chick broc", "chicken and broccolie topped with a touch of love", false, new HashSet<>(), ingredientsList3);
//		Meal meal8 = new Meal(UUID.randomUUID(), "chick broc", "chicken and broccolie topped with a touch of love", false, new HashSet<>(),ingredientsList4);
//		Meal meal9 = new Meal(UUID.randomUUID(), "chick broc", "chicken and broccolie topped with a touch of love", false, new HashSet<>(), ingredientsList5);
//		Meal meal10 = new Meal(UUID.randomUUID(), "chick broc", "chicken and broccolie topped with a touch of love", false, new HashSet<>(), ingredientsList5);
//
//		return strings -> {
//			mealRepository.save(meal1);
//			mealRepository.save(meal2);
//			mealRepository.save(meal3);
//			mealRepository.save(meal4);
//			mealRepository.save(meal5);
//			mealRepository.save(meal6);
//			mealRepository.save(meal7);
//			mealRepository.save(meal8);
//			mealRepository.save(meal9);
//			mealRepository.save(meal10);
//
//			userRepository.save(user1);
//			userRepository.save(user2);
//			userRepository.save(user3);
//			userRepository.save(user4);
//		};
//
//	}
//
//}
//
