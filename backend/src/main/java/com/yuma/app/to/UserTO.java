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
	private Set<Role> roles;
	private List<Meal> mealList;
	private List<String> dislikesList;

	@Override
	public String toString() {
		return "UserTO{" +
			"userId=" + userId +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", email='" + email + '\'' +
			", plan=" + plan +
			", active=" + enabled +
			", timestamp='" + timestamp + '\'' +
			", roles=" + roles +
			'}';
	}
}
