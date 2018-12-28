package com.yuma.app.resources;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import com.yuma.app.document.User;
import com.yuma.app.service.UserService;
import com.yuma.app.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationResourceTest {

	@Mock
	private UserService userService;

	@Mock
	BindingResult bindingResult;
	
	@InjectMocks
	private AuthenticationResource authenticationResource;
	
	private Optional<User> user;
	
	@Before
	public void setup(){ 
		user = Optional.of(new User());
	}

	@Test
	public void GivenUserWhenRegistersAndEmailAlreadyExitsThenShouldReturnConflictStatus() {
		UserTO userTO = prepareUserTo();
		
		when(userService.findUserByEmail(userTO.getEmail())).thenReturn(user);
		when(bindingResult.hasErrors()).thenReturn(true);
		HttpStatus httpStatus = authenticationResource.register(userTO, bindingResult);

		Assert.assertEquals(httpStatus, HttpStatus.CONFLICT);
	}

	@Test
	public void GivenValidUserWhenRegistersThenShouldReturnOkStatus() {
		UserTO userTO = prepareUserTo();

		when(userService.findUserByEmail(userTO.getEmail())).thenReturn(Optional.empty());
		doNothing().when(userService).saveUser(userTO);
		
		HttpStatus httpStatus = authenticationResource.register(userTO, bindingResult);

		Assert.assertEquals(httpStatus, HttpStatus.OK);
	}

	
	private UserTO prepareUserTo() {
		UserTO userTO = new UserTO();
		userTO.setUserId("123");
		userTO.setFirstName("first name");
		userTO.setLastName("last name");
		userTO.setEmail("example@email.com");
		return userTO;
	}

}
