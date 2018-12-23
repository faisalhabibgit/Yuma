package com.yuma.app.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuma.app.document.User;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest {
	
	private String email;
	private Optional<User> user1;
	private UserTO userTO;

	@MockBean
	private AuthenticationService authenticationService;

	@Before
	public void setUp() throws Exception {
		email = "jon.doe@gmail.com";
		user1 = Optional.of(new User("password1", email, true));
		userTO = new UserTO(user1.get().getPassword(), user1.get().getEmail(), user1.get().isEnabled());

		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void loadUserByUsernameTest(){
		Mockito.when(authenticationService.loadUserByUsername(email)).thenReturn();
		Assert.assertEquals(user1.get().getEmail(), email);
	}
	
	
}
