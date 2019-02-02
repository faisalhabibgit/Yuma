package com.yuma.app.security;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import com.yuma.app.service.CustomUserDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class JwtAuthFilterTest {
	
	@Mock
	private HttpServletRequest httpServletRequest;

	@Mock
	private HttpServletResponse httpServletResponse;

	@Mock
	private FilterChain filterChain;

	@Mock
	private JwtTokenProvider tokenProvider;
	
	@Mock
	private CustomUserDetailsService customUserDetailsService;

	@InjectMocks
	private JwtAuthFilter jwtAuthFilter;
	
	String token = "thisisatoken";
	
	String jwt = "Bearer " + token;
	
	String userId = "123abc";

	@Test
	public void testFilterInternal() throws IOException, ServletException {

		UserDetails userDetails = prepareUserPrincipal();
		((UserPrincipal) userDetails).setAuthorities(new ArrayList<>());
		
		when(httpServletRequest.getHeader("Authorization")).thenReturn(jwt);
		when(tokenProvider.validateToken(token)).thenReturn(true);
		when(tokenProvider.getUserIdFromJWT(token)).thenReturn(userId);
		when(customUserDetailsService.loadUserById(userId)).thenReturn(userDetails);
		
		try {
			jwtAuthFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
		} catch (ServletException| IOException e) {
			e.printStackTrace();
		}

		verify(httpServletRequest).getHeader("Authorization");
		verify(tokenProvider).validateToken(token);
		verify(tokenProvider).getUserIdFromJWT(token);
		verify(customUserDetailsService).loadUserById(userId);
		verify(filterChain).doFilter(httpServletRequest, httpServletResponse);

	}
	
	@Test
	public void givenHttpRequestWithJwtWhenGetJwtCalledThenShouldReturnJwtToken() {
		when(httpServletRequest.getHeader("Authorization")).thenReturn(jwt);

		String resultToken = jwtAuthFilter.getJwtFromRequest(httpServletRequest);

		Assert.assertEquals(resultToken, token);
		
	}

	@Test
	public void givenHttpRequestWithJwtTokenEmptyCalledThenShouldReturnNull() {
		when(httpServletRequest.getHeader("Authorization")).thenReturn("");

		String resultToken = jwtAuthFilter.getJwtFromRequest(httpServletRequest);

		Assert.assertNull(resultToken);

	}
	
	UserPrincipal prepareUserPrincipal() {
		UserPrincipal userPrincipal = new UserPrincipal(userId, "first name", "last name", null, true, null, null);
		return userPrincipal;
	}

}
