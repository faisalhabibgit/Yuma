package com.yuma.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuma.app.document.Role;
import com.yuma.app.document.User;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private Optional<Role> role;
	@Mock
	private UserRepository userRepository;
	@Mock
	private RoleRepository roleRepository;
	@Mock
	private ConversionService conversionService;
	@Mock
	private PasswordEncoder passwordEncoder;


	@InjectMocks
	private UserService userService;
	
	private String adminRole = "ADMIN";

	@Before
	public void setUp() throws Exception {
		role = Optional.of(new Role());
	}

	@Test
	public void givenUserWhenSaveUserCalledThenShouldSaveTheUserRecord() {

		SignUpRequest signUpRequest = prepareSignupReq();
		User user = prepareUser();

		when(conversionService.convert(signUpRequest, User.class)).thenReturn(user);
		when(roleRepository.findByRole(adminRole)).thenReturn(role);


		userService.saveUser(signUpRequest);

		verify(conversionService, times(1)).convert(signUpRequest, User.class);
		verify(roleRepository, times(1)).findByRole(adminRole);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	public void whenListCalledThenShouldReturnListOfUsers() {

		List<User> users = prepareUserList();
		List<UserTO> userTOs = prepareUserTOList();

		when(conversionService.convert(users.get(0), UserTO.class)).thenReturn(userTOs.get(0));
		when(conversionService.convert(users.get(1), UserTO.class)).thenReturn(userTOs.get(1));
		when(userRepository.findAll()).thenReturn(users);


		List<UserTO> retrievedListOfUsers = userService.list();

		verify(conversionService, times(1)).convert(users.get(0), UserTO.class);
		verify(conversionService, times(1)).convert(users.get(1), UserTO.class);
		verify(userRepository, times(1)).findAll();
		Assert.assertEquals(retrievedListOfUsers.size(), 2);

	}

	@Test
	public void givenEmailWhenFindByEmailCalledThenShouldReturnUserWithThatEmail() {

		String email = "example@email.com";

		User user = new User();
		user.setEmail(email);

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

		userService.findUserByEmail(email);

		Assert.assertEquals(user.getEmail(), email);

	}
	
	@Test
	public void testDeleteUser() {
		
		String userId = "123";
		
		doNothing().when(userRepository).delete(userId);
		
		userService.deleteUserByUserID(userId);
		
		
	}

	@Test
	public void testCreateUser() {

		UserTO userTO = prepareUserTo();
		User user = prepareUser();
		
		when(conversionService.convert(userTO, User.class)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		when(conversionService.convert(user, UserTO.class)).thenReturn(userTO);

		userService.create(userTO);
		
	}

	@Test
	public void testUserUpdate() {

		UserTO userTO = prepareUserTo();
		userTO.setUserId("234");
		User user = prepareUser();

		when(userRepository.findByUserId(userTO.getUserId())).thenReturn(Optional.of(user));
		when(conversionService.convert(userTO, User.class)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
		when(conversionService.convert(user, UserTO.class)).thenReturn(userTO);

		userService.updateUser(userTO);

	}
	
	@Test
	public void testExistByEmail() {
		
		String email = "example@gmail.com";
		when(userRepository.existsByEmail(email)).thenReturn(true);
		
		userService.existsByEmail(email);
		
		verify(userRepository).existsByEmail(email);
	}

	private SignUpRequest prepareSignupReq() {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setFirstName("first name");
		signUpRequest.setLastName("last name");
		signUpRequest.setEmail("example@email.com");
		signUpRequest.setPassword("myPass");
		return signUpRequest;
	}


	private User prepareUser() {
		User user = new User();
		user.setFirstName("first name");
		user.setLastName("last name");
		user.setEmail("example@email.com");
		user.setPassword("myPass");
		return user;
	}

	private List<User> prepareUserList() {

		List<User> users = new ArrayList<User>() {
			{
				add(new User());
				add(new User());
			}
		};
		return users;
	}

	private List<UserTO> prepareUserTOList() {

		List<UserTO> userTOs = new ArrayList<UserTO>() {
			{
				add(new UserTO());
				add(new UserTO());
			}
		};
		return userTOs;
	}
	
	private UserTO prepareUserTo(){
		
		UserTO userTO = new UserTO();
		return userTO;
	}
}
