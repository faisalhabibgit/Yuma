package com.yuma.app.converter;


import com.yuma.app.document.User;
import com.yuma.app.payload.SignUpRequest;
import org.junit.Assert;
import org.junit.Test;

public class SignupRequestConverterTest {
	
	@Test
	public void testSignUpRequestToUserConverter() {
		
		SignUpRequest signUpRequest = prepareSignUpRequest();
		
		SignupRequestConverter signupRequestConverter = new SignupRequestConverter();
		User user = signupRequestConverter.convert(signUpRequest);

		Assert.assertEquals(user.getFirstName(), signUpRequest.getFirstName());
		Assert.assertEquals(user.getLastName(), signUpRequest.getLastName());
		Assert.assertEquals(user.getEmail(), signUpRequest.getEmail());
		Assert.assertEquals(user.getPassword(), signUpRequest.getPassword());
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
