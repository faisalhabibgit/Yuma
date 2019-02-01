package com.yuma.app.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
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
	private List<Meal> mealList = new ArrayList<>();
	private List<String> dislikesList;
	private List<String> likes;

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
