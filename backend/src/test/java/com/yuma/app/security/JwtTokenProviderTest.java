package com.yuma.app.security;

import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;


import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenProviderTest {
	
	@InjectMocks
	JwtTokenProvider jwtTokenProvider;
	
	@Mock
	Authentication authentication;
	
	String userId = "1234";
	
	@Test
	public void givenAuthenticationWhenGenerateTokenCalledShouldReturnWithAToken() {

		UserPrincipal principal = prepareUserPrincipal();
		
		when(authentication.getPrincipal()).thenReturn(principal);
		ReflectionTestUtils.setField(jwtTokenProvider, "jwtSecret", "test");
		String token = jwtTokenProvider.generateToken(authentication);

		Assert.notNull(token);
	}
	
	UserPrincipal prepareUserPrincipal() {
		UserPrincipal userPrincipal = new UserPrincipal(userId, "first name", "last name", null,
			null, true, null, null, null);
		return userPrincipal;
	}

}
