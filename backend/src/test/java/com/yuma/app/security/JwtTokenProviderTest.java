package com.yuma.app.security;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.lang.Assert;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenProviderTest {
	
	@InjectMocks
	private JwtTokenProvider jwtTokenProvider;
	
	@Mock
	private Authentication authentication;
	
	String userId = "1234";
	
	@Test
	public void givenAuthenticationWhenGenerateTokenCalledShouldReturnWithAToken() {

		UserPrincipal principal = prepareUserPrincipal();
		
		when(authentication.getPrincipal()).thenReturn(principal);
		ReflectionTestUtils.setField(jwtTokenProvider, "jwtSecret", "test");
		String token = jwtTokenProvider.generateToken(authentication);

		Assert.notNull(token);
	}
	
	private UserPrincipal prepareUserPrincipal() {
		UserPrincipal userPrincipal = new UserPrincipal(userId, "first name", "last name", null, true, null, null);
		return userPrincipal;
	}

}
