package com.yuma.app.document;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Admin {
	
	@Id
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private boolean isActive;
	private Set<Role> roles;
}
