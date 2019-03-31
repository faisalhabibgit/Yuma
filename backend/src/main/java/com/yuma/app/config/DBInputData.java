package com.yuma.app.config;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Plan;
import com.yuma.app.document.Role;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;


@Slf4j
@EnableMongoRepositories(basePackageClasses = RoleRepository.class)

@Configuration
public class DBInputData {

	private static List<Consumer> parseJsonFileAndCreateAUserList() {
		List<Consumer> usersList = null;

		JSONParser parser = new JSONParser();

		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/preferences_parsed.json"));
			Object obj = parser.parse(reader);

			JSONObject jsonObject = (JSONObject) obj;
			JSONObject users = (JSONObject) jsonObject.get("users");

			usersList = new ArrayList<>();
			Consumer user;
			Plan plan;
			JSONArray jsonArray;

			int counter = 1;

			for (Object key : users.keySet()) {
				user = new Consumer();
				plan = new Plan();
				List<String> likes = new ArrayList<>();
				List<String> dislikes = new ArrayList<>();
				user.setLikes(likes);
				user.setDislikesList(dislikes);
				user.setPlan(plan);
				jsonArray = (JSONArray) users.get(key);

				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonobject = (JSONObject) jsonArray.get(i);

					String like = (String) jsonobject.get("general");
					if (like != null) {
						user.getLikes().add(like);
					}

					String numOfMeals = (String) jsonobject.get("plan");
					if (numOfMeals != null) {
						user.getPlan()
							.setNumOfMeals(Integer.parseInt(numOfMeals));
					}

					String dislike = (String) jsonobject.get("allergy");
					if (dislike != null) {
						user.getDislikesList().add(dislike);
					}

				}
				user.setFirstName("user" + counter);
				user.setLastName("");
				user.setEmail("");
				user.setActive(true);
				usersList.add(user);
				counter++;
			}

			for (Consumer userCreated : usersList) {
				System.out.println("user info: " + "number of meals: " + userCreated.getPlan().getNumOfMeals() + " likes: " + userCreated.getLikes().toString()
					+ " dislikes: " + userCreated.getLikes().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usersList;
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

	@Bean
	CommandLineRunner populateProductionUsers(UserRepository userRepository) {
		List<Consumer> consumers = parseJsonFileAndCreateAUserList();
		return args -> {
			for (Consumer consumer : consumers) {
				userRepository.save(consumer);
			}
		};
	}
}
