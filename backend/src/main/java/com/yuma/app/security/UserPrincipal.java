package com.yuma.app.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuma.app.document.Preferences;
import com.yuma.app.document.User;

@Setter
@Getter
public class UserPrincipal implements UserDetails {

	private String userId;

	private String firstName;

	private String lastName;
	
	private Preferences preferences;

	private boolean enabled;
	
	private String timestamp;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(String userId, String firstName, String lastName, String email, Preferences preferences, boolean enabled, String timestamp, String password, Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.preferences = preferences;
		this.enabled = enabled;
		this.timestamp = timestamp;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
			new SimpleGrantedAuthority(role.getRole())
		).collect(Collectors.toList());

		return new UserPrincipal(
			user.getUserId(),
			user.getFirstName(),
			user.getLastName(),
			user.getEmail(),
			user.getPreferences(),
			user.isEnabled(),
			user.getTimestamp(),
			user.getPassword(),
			authorities
		);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(userId, that.userId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(userId);
	}
}
