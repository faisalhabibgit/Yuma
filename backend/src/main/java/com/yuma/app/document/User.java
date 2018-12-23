package com.yuma.app.document;

import java.util.Set;
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
public class User {

	@Id
	private UUID userId;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Preferences preferences;
	private boolean enabled;
	private String timestamp;
	private Set<Role> roles;

	public User(String password, String email, boolean enabled) {
		this.userId = UUID.randomUUID();
		this.password = password;
		this.email = email;
		this.enabled = enabled;
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
