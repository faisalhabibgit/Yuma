package com.yuma.app.document;

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
public class Consumer {

	@Id
	private UUID userId;
	private String firstName;
	private String lastName;
	private String personalEmail;
	private String workEmail;
	private Preferences preferences;
	private String timestamp;

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
