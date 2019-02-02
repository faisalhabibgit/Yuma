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
import com.yuma.app.document.Admin;

@Setter
@Getter
public class UserPrincipal implements UserDetails {

	private String userId;

	private String firstName;

	private String lastName;
	
	private boolean isActive;
	
	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(String userId, String firstName, String lastName, String email, boolean isActive, String password, Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.authorities = authorities;
	}

	public static UserPrincipal create(Admin admin) {
		List<GrantedAuthority> authorities = admin.getRoles().stream().map(role ->
			new SimpleGrantedAuthority(role.getRole())
		).collect(Collectors.toList());

		return new UserPrincipal(
			admin.getUserId(),
			admin.getFirstName(),
			admin.getLastName(),
			admin.getEmail(),
			admin.isActive(),
			admin.getPassword(),
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
		return isActive;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isActive;
	}
	
	@Override
	public boolean isEnabled() {
		return isActive;
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
