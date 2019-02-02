package com.yuma.app.converter;


import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.Admin;
import com.yuma.app.payload.SignUpRequest;

public class SignupRequestConverterTest {
	@Test
	public void testSignUpRequestToUserConverter() {
		SignUpRequest signUpRequest = prepareSignUpRequest();
		SignupRequestConverter signupRequestConverter = new SignupRequestConverter();
		Admin admin = signupRequestConverter.convert(signUpRequest);

		Assert.assertEquals(admin.getFirstName(), signUpRequest.getFirstName());
		Assert.assertEquals(admin.getLastName(), signUpRequest.getLastName());
		Assert.assertEquals(admin.getEmail(), signUpRequest.getEmail());
		Assert.assertEquals(admin.getPassword(), signUpRequest.getPassword());
	}

	private SignUpRequest prepareSignUpRequest() {
		SignUpRequest signUpRequest = new SignUpRequest();

		signUpRequest.setFirstName("first name");
		signUpRequest.setLastName("first name");
		signUpRequest.setPassword("password");
		signUpRequest.setEmail("test@gmail.com");
		return signUpRequest;
	}
}
