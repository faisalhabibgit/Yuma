package com.yuma.app.to;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.yuma.app.document.Meal;
import com.yuma.app.document.Plan;
import com.yuma.app.document.Role;
import com.yuma.app.document.enums.Allergens;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerTO {

	private String userId;
	private String firstName;
	private String password;
	private String lastName;
	private String email;
	private Plan plan;
	private boolean enabled;
	private String timestamp;
	private String company;
	private String yumaServerId;
	private Set<Role> roles;
	private List<Meal> mealList;
	private Set<Allergens> allergies;
	private List<String> dislikesList;
	private List<String> likes;
}
