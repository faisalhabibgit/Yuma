package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Admin;
import com.yuma.app.payload.SignUpRequest;

public class SignupRequestConverter implements Converter<SignUpRequest, Admin> {
	@Override
	public Admin convert(SignUpRequest signUpRequest) {
		Admin admin = new Admin();
		admin.setFirstName(signUpRequest.getFirstName());
		admin.setLastName(signUpRequest.getLastName());
		admin.setEmail(signUpRequest.getEmail());
		admin.setPassword(signUpRequest.getPassword());
		return admin;
	}
}
