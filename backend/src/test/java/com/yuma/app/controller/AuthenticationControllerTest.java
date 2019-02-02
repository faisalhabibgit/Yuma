package com.yuma.app.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import com.yuma.app.payload.LoginRequest;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.security.JwtTokenProvider;
import com.yuma.app.service.AdminService;

@RunWith(MockitoJUnitRunner.class)

public class AuthenticationControllerTest {

	@InjectMocks
	AuthenticationController authenticationController;

	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	AdminService adminService;

	@Mock
	JwtTokenProvider jwtTokenProvider;


	@Test
	public void TestAuthenticateUser() {

		LoginRequest loginRequest = prepareLoginRequest();
		authenticationController.authenticateUser(loginRequest);

		verify(authenticationManager).authenticate(any());
		verify(jwtTokenProvider).generateToken(any());
	}

	@Test
	public void givenSignUpRequestWithExistingEmailWhenRegisterUserThenShouldReturnBadRequest() {

		SignUpRequest signUpRequest = prepareSignUpRequest();

		when(adminService.existsByEmail(signUpRequest.getEmail())).thenReturn(true);

		ResponseEntity<?>  responseEntity = authenticationController.registerUser(signUpRequest);

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	private LoginRequest prepareLoginRequest() {

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail("testemail@gmail.com");
		loginRequest.setPassword("password");

		return loginRequest;
	}

	private SignUpRequest prepareSignUpRequest() {

		SignUpRequest signUpRequest = new SignUpRequest();

		signUpRequest.setEmail("test@gmail.com");
		signUpRequest.setFirstName("First name");
		signUpRequest.setLastName("Last name");
		signUpRequest.setPassword("test@gmial.com");

		return signUpRequest;
	}
}

