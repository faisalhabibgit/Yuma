//package com.yuma.app.service;
//
//import java.util.UUID;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.yuma.app.document.User;
//import com.yuma.app.to.UserTO;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AuthenticationServiceTest {
//	
//	private String email;
//	private User user1;
//	private UserTO userTO;
//	private UserDetails userDetails;
//	
//	@Autowired
//	private UserService userService;
//
//	@Mock
//	private AuthenticationService authenticationService;
//	
//	@Before
//	public void setUp() throws Exception {
//		email = "jon.doe@gmail.com";
//		user1 = new User(UUID.randomUUID(), "pw", email, true);
//		userTO = new UserTO(user1.getUserId(), user1.getPassword(), user1.getEmail(), user1.isEnabled());
//		userService.saveUser(userTO);
//
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void loadUserByUsernameTest(){
//		Mockito.when(authenticationService.loadUserByUsername(email)).thenReturn(userDetails);
//		Assert.assertEquals(userDetails.getUsername(), email);
//	}
//	
//	
//}
