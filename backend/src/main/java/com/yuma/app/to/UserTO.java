package com.yuma.app.to;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.yuma.app.document.Preferences;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTO {
	
	private UUID userId;
	private String firstName;
	private String lastName;
	private String personalEmail;
	private String workEmail;
	private Preferences preferences;
	private String timestamp;

	@Override
	public String toString() {
		return "UserTO{" +
			"userId=" + userId +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", personalEmail='" + personalEmail + '\'' +
			", workEmail='" + workEmail + '\'' +
			", preferences=" + preferences +
			", timestamp='" + timestamp + '\'' +
			'}';
	}
}
