package com.yuma.app.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
	public String firstName;
	public String lastName;
	public String password;
	public String email;
}
