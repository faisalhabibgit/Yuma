package com.yuma.app.to;

import java.util.List;
import java.util.Set;

import com.yuma.app.document.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.yuma.app.document.Meal;
import com.yuma.app.document.Plan;
import com.yuma.app.document.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTO {

	private String userId;
	private String firstName;
	private String password;
	private String lastName;
	private String email;
	private Plan plan;
	private boolean enabled;
	private String timestamp;
	private String yumaServerId;
	private Company company;
	private Set<Role> roles;
	private List<Meal> mealList;
	private List<String> dislikesList;
}
