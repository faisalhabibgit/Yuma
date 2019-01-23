package com.yuma.app.document;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


	@Id
	private String userId;

	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Plan plan;
	private boolean isActive;
	private String timestamp;
	@DBRef
	private Set<Role> roles;
	private List<Meal> mealList;
	private List<String> dislikesList;

	public User(String userId,String firstName, String lastName, String email, String password, boolean isActive, Plan plan, List<String> dislikesList) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.plan = plan;
		this.dislikesList = dislikesList;
	}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public void updateFrom(User userToUpdate) {
		this.setFirstName(userToUpdate.getFirstName());
		this.setLastName(userToUpdate.getLastName());
		this.setEmail(userToUpdate.getEmail());
		this.setPlan(userToUpdate.getPlan());

	}

	@Override
	public String toString() {
		return "User{" +
			"userId=" + userId +
			", password='" + password + '\'' +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", email='" + email + '\'' +
			", plan=" + plan +
			", isActive=" + isActive +
			", timestamp='" + timestamp + '\'' +
			", roles=" + roles +
			'}';
	}
}
