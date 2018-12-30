package com.yuma.app.document;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
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
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Preferences preferences;
	private boolean enabled;
	private String timestamp;
	@DBRef
	private Set<Role> roles;

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

	@Override
	public String toString() {
		return "User{" +
			"userId=" + userId +
			", password='" + password + '\'' +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", email='" + email + '\'' +
			", preferences=" + preferences +
			", enabled=" + enabled +
			", timestamp='" + timestamp + '\'' +
			", roles=" + roles +
			'}';
	}
}
