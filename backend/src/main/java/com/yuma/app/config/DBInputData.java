package com.yuma.app.config;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Ingredients;
import com.yuma.app.document.Meal;
import com.yuma.app.document.Plan;
import com.yuma.app.document.Role;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;


@Slf4j
@EnableMongoRepositories(basePackageClasses = RoleRepository.class)

@Configuration
public class DBInputData {

//	private static List<Consumer> parseJsonFileAndCreateAUserList() {
//		List<Consumer> usersList = null;
//
//		JSONParser parser = new JSONParser();
//
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader("backend/src/main/resources/preferences_parsed.json"));
//			Object obj = parser.parse(reader);
//
//			JSONObject jsonObject = (JSONObject) obj;
//			JSONObject users = (JSONObject) jsonObject.get("users");
//
//			usersList = new ArrayList<>();
//			Consumer user;
//			Plan plan;
//			JSONArray jsonArray;
//
//			int counter = 1;
//
//			for (Object key : users.keySet()) {
//				user = new Consumer();
//				plan = new Plan();
//				List<String> likes = new ArrayList<>();
//				List<String> dislikes = new ArrayList<>();
//				user.setLikes(likes);
//				user.setDislikesList(dislikes);
//				user.setPlan(plan);
//				jsonArray = (JSONArray) users.get(key);
//
//				for (int i = 0; i < jsonArray.size(); i++) {
//					JSONObject jsonobject = (JSONObject) jsonArray.get(i);
//
//					String like = (String) jsonobject.get("general");
//					if (like != null) {
//						user.getLikes().add(like);
//					}
//
//					String numOfMeals = (String) jsonobject.get("plan");
//					if (numOfMeals != null) {
//						user.getPlan().setNumOfMeals(Integer.parseInt(numOfMeals));
//					}
//
//					String dislike = (String) jsonobject.get("allergy");
//					if (dislike != null) {
//						user.getDislikesList().add(dislike);
//					}
//
//				}
//				user.setFirstName("user" + counter);
//				user.setLastName("");
//				user.setEmail("");
//				user.setActive(true);
//				usersList.add(user);
//				counter++;
//			}
//
//			for (Consumer userCreated : usersList) {
//				System.out.println("user info: " + "number of meals: " + userCreated.getPlan().getNumOfMeals() + " likes: " + userCreated.getLikes().toString()
//					+ " dislikes: " + userCreated.getLikes().toString());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return usersList;
//	}


	@Bean	
	CommandLineRunner generateData (MealRepository mealRepository, UserRepository userRepository){	
		ArrayList<Ingredients> ingredientsList1 = new ArrayList<>();	
		ArrayList<Ingredients> ingredientsList2 = new ArrayList<>();	
		ArrayList<Ingredients> ingredientsList3 = new ArrayList<>();	
		ArrayList<Ingredients> ingredientsList4 = new ArrayList<>();	
		ArrayList<Ingredients> ingredientsList5 = new ArrayList<>();	
		//bogus ingredients	
		Ingredients chickpeas = new Ingredients("chickpeas", 123.343, 0.002, 59.00, false);	
		Ingredients cheese = new Ingredients("cheese", 3.343, 0.02, 58.00, false);	
		Ingredients chicken = new Ingredients("chicken", 13.343, 0.2, 57.00, false);	
		Ingredients brocolie = new Ingredients("brocolie", 1.343, 0.000234, 5.00, false);	
		Ingredients beef = new Ingredients("beef", 1.343, 0.000234, 5.00, false);	
		Ingredients rice = new Ingredients("rice", 1.343, 0.000234, 5.00, false);	
		Ingredients tomato = new Ingredients("tomato", 1.343, 0.000234, 5.00, false);	
		Ingredients raisin = new Ingredients("raisin", 1.343, 0.000234, 5.00, false);	
		Ingredients banana = new Ingredients("banana", 1.343, 0.000234, 5.00, false);	
		Ingredients fish = new Ingredients("fish", 1.343, 0.000234, 5.00, false);	
		Ingredients potato = new Ingredients("potato", 1.343, 0.000234, 5.00, false);	
		Ingredients nuts = new Ingredients("nuts", 1.343, 0.000234, 5.00, false);	
		Ingredients lamb = new Ingredients("lamb", 1.343, 0.000234, 5.00, false);	
		Ingredients nutsOptional = new Ingredients("nuts", 1.343, 0.000234, 5.00, true);	
		Ingredients onionOptional = new Ingredients("onion", 1.343, 0.000234, 5.00, true);	
		Ingredients chickpeasOptional = new Ingredients("chickpeas", 1.343, 0.000234, 5.00, true);	
		Ingredients pork = new Ingredients("pork", 1.343, 0.000234, 5.00, true);	
		Ingredients milk = new Ingredients("milk", 1.343, 0.000234, 5.00, false);	


		ingredientsList1.add(chickpeas);	
		ingredientsList1.add(cheese);	
		ingredientsList1.add(chicken);	
		ingredientsList1.add(brocolie);	

		ingredientsList2.add(beef);	
		ingredientsList2.add(rice);	
		ingredientsList2.add(tomato);	
		ingredientsList2.add(raisin);	
		ingredientsList2.add(banana);	

		ingredientsList3.add(fish);	
		ingredientsList3.add(rice);	
		ingredientsList3.add(potato);	
		ingredientsList3.add(nuts);	

		ingredientsList4.add(lamb);	
		ingredientsList4.add(nutsOptional);	
		ingredientsList4.add(onionOptional);	
		ingredientsList4.add(chickpeasOptional);	
		ingredientsList4.add(banana);	

		ingredientsList5.add(pork);	
		ingredientsList5.add(potato);	
		ingredientsList5.add(milk);	
		ingredientsList5.add(chicken);	


		List<String> dislikes1 = new ArrayList<>();	
		List<String> dislikes2 = new ArrayList<>();	
		List<String> dislikes3 = new ArrayList<>();	
		List<String> dislikes4 = new ArrayList<>();	

		String dislikeOnion = "onion";	
		String dislikenuts = "nuts";	
		String disliketomato = "tomato";	

		dislikes1.add(dislikeOnion);	
		dislikes1.add(dislikenuts);	

		dislikes2.add(disliketomato);	

		dislikes4.add(dislikeOnion);	

		Plan plan1 = new Plan(4,false,false, new HashSet<>());	
		Plan plan2 = new Plan(2,false,false, new HashSet<>());	
		Plan plan3 = new Plan(7,false,false, new HashSet<>());	
		Plan plan4 = new Plan(5,false,false, new HashSet<>());	

		Consumer user1 = Consumer.builder().userId(UUID.randomUUID().toString()).firstName("ahmad").lastName("baiazid").email("ahmad.baiazid@hotmail.com").password("pass2").isActive(true).dislikesList(dislikes1).plan(plan1).build();	
		Consumer user2 = Consumer.builder().userId(UUID.randomUUID().toString()).firstName("fais").lastName("habib").email("fais.habib@hotmail.com").password("pass2").isActive(true).dislikesList(dislikes2).plan(plan2).build();	
		Consumer user3 = Consumer.builder().userId(UUID.randomUUID().toString()).firstName("nate").lastName("nathy").email("nate.nathy@hotmail.com").password("pass2").isActive(true).dislikesList(dislikes3).plan(plan3).build();	
		Consumer user4 = Consumer.builder().userId(UUID.randomUUID().toString()).firstName("john").lastName("doe").email("john.doe@hotmail.com").password("pass2").isActive(true).dislikesList(dislikes4).plan(plan4).build();	


		Meal meal1 = Meal.builder().mealId(UUID.randomUUID().toString()).name("chick broc").description("chicken and broccolie topped with a touch of love").isAvailable(true).ingredients(ingredientsList1).mealScore(24).build(); 	
		Meal meal2 = Meal.builder().mealId(UUID.randomUUID().toString()).name("beef and rice").description("beef and rice topped with a touch of love").isAvailable(true).ingredients(ingredientsList2).mealScore(52).build(); 	
		Meal meal3 = Meal.builder().mealId(UUID.randomUUID().toString()).name("fish and rice").description("beef and rice topped with a touch of love").isAvailable(true).ingredients(ingredientsList3).mealScore(28).build(); 	
		Meal meal4 = Meal.builder().mealId(UUID.randomUUID().toString()).name("lamb").description("chicken and broccolie topped with a touch of love").isAvailable(true).ingredients(ingredientsList4).mealScore(20).build(); 	
		Meal meal5 = Meal.builder().mealId(UUID.randomUUID().toString()).name("pork").description("chicken and broccolie topped with a touch of love").isAvailable(true).ingredients(ingredientsList5).mealScore(40).build(); 	
		Meal meal6 = Meal.builder().mealId(UUID.randomUUID().toString()).name("fish and rice").description("chicken and broccolie topped with a touch of love").isAvailable(false).ingredients(ingredientsList2).mealScore(57).build();	
		Meal meal7 = Meal.builder().mealId(UUID.randomUUID().toString()).name("chick broc").description("chicken and broccolie topped with a touch of love").isAvailable(false).ingredients(ingredientsList3).mealScore(23).build();	
		Meal meal8 = Meal.builder().mealId(UUID.randomUUID().toString()).name("chick broc").description("chicken and broccolie topped with a touch of love").isAvailable(true).ingredients(ingredientsList4).mealScore(32).build();	
		Meal meal9 = Meal.builder().mealId(UUID.randomUUID().toString()).name("chick broc").description("chicken and broccolie topped with a touch of love").isAvailable(false).ingredients(ingredientsList5).mealScore(45).build();	
		Meal meal10 = Meal.builder().mealId(UUID.randomUUID().toString()).name("chick broc").description("chicken and broccolie topped with a touch of love").isAvailable(true).ingredients(ingredientsList5).mealScore(50).build();	

		return strings -> {	
			mealRepository.save(meal1);	
			mealRepository.save(meal2);	
			mealRepository.save(meal3);	
			mealRepository.save(meal4);	
			mealRepository.save(meal5);	
			mealRepository.save(meal6);	
			mealRepository.save(meal7);	
			mealRepository.save(meal8);	
			mealRepository.save(meal9);	
			mealRepository.save(meal10);	

			userRepository.save(user1);	
			userRepository.save(user2);	
			userRepository.save(user3);	
			userRepository.save(user4);	
		};	
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {

			Optional<Role> adminRole = roleRepository.findByRole("ADMIN");
			if (!adminRole.isPresent()) {
				Role newAdminRole = new Role();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}

			Optional<Role> userRole = roleRepository.findByRole("USER");
			if (!userRole.isPresent()) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}
		};
	}
		
//	@Bean
//	CommandLineRunner populateProductionUsers(UserRepository userRepository) {
//		List<Consumer> consumers = parseJsonFileAndCreateAUserList();
//		return args -> {
//			for (Consumer consumer : consumers) {
//				userRepository.save(consumer);
//			}
//		};
//	}
}
