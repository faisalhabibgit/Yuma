package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.User;
import com.yuma.app.payload.SignUpRequest;

public class SignupRequestConverter implements Converter<SignUpRequest, User> {
	@Override
	public User convert(SignUpRequest signUpRequest) {
		User user = new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(signUpRequest.getPassword());
		return user;
	}
}
