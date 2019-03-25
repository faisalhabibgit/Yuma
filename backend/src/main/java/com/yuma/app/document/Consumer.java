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
public class Consumer {
	
	@Id
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Plan plan;
	private boolean isActive;
	private String timestamp;
	private Company company;
	private String yumaServerId;

	@DBRef
	private Set<Role> roles;
	private List<Meal> mealList = new ArrayList<>();
	private List<String> dislikesList;
	private List<String> likes;

	public void updateFrom(Consumer consumerToUpdate) {
		this.setFirstName(consumerToUpdate.getFirstName());
		this.setLastName(consumerToUpdate.getLastName());
		this.setEmail(consumerToUpdate.getEmail());
		this.setPlan(consumerToUpdate.getPlan());
		this.setActive(consumerToUpdate.isActive());
	}
}
