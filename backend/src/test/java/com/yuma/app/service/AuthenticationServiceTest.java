//package com.yuma.app.service;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
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
//	private UserDetails userDetails;
//	
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private AuthenticationService authenticationService;
//	
//	@Before
//	public void setUp() throws Exception {
//		email = "jon.doe@gmail.com";
//		User user1 = new User(email, "password");
//		UserTO userTO= new UserTO(user1.getEmail(), user1.getPassword());
//		userService.saveUser(userTO);
//
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void loadUserByUsernameTest(){
//		userDetails = authenticationService.loadUserByUsername(email);
//		//Mockito.when(authenticationService.loadUserByUsername(email)).thenReturn(userDetails);
//		Assert.assertEquals(userDetails.getUsername(), email);
//	}
//	
//	
//}
