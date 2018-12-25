package com.yuma.app.service;

import com.yuma.app.document.Role;
import com.yuma.app.document.User;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;

	@Mock
	private ConversionService conversionService;
	
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Mock
	private Logger logger;

	@InjectMocks
	private UserService userService;
	
	Optional<Role> role;
	
	@Before
	public void setUp() throws Exception {
		role = Optional.of(new Role());
	}
	
	private String adminRole = "ADMIN";

	@Test
	public void givenUserWhenSaveUserCalledThenShouldSaveTheUserRecord() {
		
		UserTO userTO = prepareUserTo();
		User user = prepareUser();
		
		when(conversionService.convert(userTO, User.class)).thenReturn(user);
		when(roleRepository.findByRole(adminRole)).thenReturn(role);


		userService.saveUser(userTO);

		verify(conversionService, times(1)).convert(userTO, User.class);
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



	private UserTO prepareUserTo() {
		UserTO userTO = new UserTO();
		userTO.setUserId("123");
		userTO.setFirstName("first name");
		userTO.setLastName("last name");
		userTO.setEmail("example@email.com");
		return userTO;
	}


	private User prepareUser() {
		User user = new User();
		user.setUserId("123");
		user.setFirstName("first name");
		user.setLastName("last name");
		user.setEmail("example@email.com");
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
}
