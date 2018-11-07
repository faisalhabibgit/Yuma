package com.yuma.app.document;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Consumer {

	@Id
	private final UUID userId;
	private final String firstName;
	private final String lastName;
	private final String personalEmail;
	private final String workEmail;
	private final Preferences preferences;
	private final String timestamp;

	public Consumer(UUID userId, String firstName, String lastName, String personalEmail, String workEmail, Preferences preferences, String timestamp) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalEmail = personalEmail;
		this.workEmail = workEmail;
		this.preferences = preferences;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Consumer{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", personalEmail='" + personalEmail + '\'' +
				", workEmail='" + workEmail + '\'' +
				", preferences=" + preferences +
				", timestamp=" + timestamp +
				'}';
	}
}
