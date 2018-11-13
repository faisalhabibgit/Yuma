package com.yuma.app.document;

import java.util.ArrayList;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Caterer {

	@Id
	private UUID userId;
	private String name;
	private String email;
	private Address address;
	private String specialty;
	private ArrayList<Meal> meals;
	private String timestamp;


	@Override
	public String toString() {
		return "Caterer{" +
			"userID=" + userId +
			", name=" + name + '\'' +
			", email=" + email + '\'' +
			", address=" + address + '\'' +
			", specialty=" + specialty + '\'' +
			", meals=" + meals +
			", timestamp=" + timestamp +
			'}';

	}

	public void updateFrom(Caterer catererToUpdate) {
		this.setName(catererToUpdate.getName());
		this.setEmail(catererToUpdate.getEmail());
		this.setAddress(catererToUpdate.getAddress());
		this.setSpecialty(catererToUpdate.getSpecialty());
		this.setMeals(catererToUpdate.getMeals());
		this.setTimestamp(catererToUpdate.getTimestamp());
	}


}
