package com.yuma.app.to;

import java.util.Set;

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
	
	private String userId;
	private String firstName;
	private String password;
	private String lastName;
	private String email;
	private Preferences preferences;
	private boolean enabled;
	private String timestamp;
	private Set<Role> roles;

	public UserTO(String uuid,String password, String email, boolean enabled) {
		this.userId = uuid;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
	}

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
