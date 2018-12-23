package com.yuma.app.to;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.yuma.app.document.Preferences;
import com.yuma.app.document.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTO {
	
	private UUID userId;
	private String firstName;
	private String password;
	private String lastName;
	private String email;
	private Preferences preferences;
	private boolean enabled;
	private String timestamp;
	private Set<Role> roles;

	@Override
	public String toString() {
		return "UserTO{" +
			"userId=" + userId +
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
