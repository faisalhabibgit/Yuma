package com.yuma.app.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Role;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerServiceTest {

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
	public void GivenUserWhenSaveUserCalledThenShouldSaveTheUserRecord() {

		SignUpRequest signUpRequest = prepareSignupReq();
		Consumer consumer = prepareUser();

		when(conversionService.convert(signUpRequest, Consumer.class)).thenReturn(consumer);
		when(roleRepository.findByRole(adminRole)).thenReturn(role);


		userService.saveUser(signUpRequest);

		verify(conversionService, times(1)).convert(signUpRequest, Consumer.class);
		verify(roleRepository, times(1)).findByRole(adminRole);
		verify(userRepository, times(1)).save(consumer);
	}

	@Test
	public void WhenListCalledThenShouldReturnListOfUsers() {

		List<Consumer> consumers = prepareUserList();
		List<UserTO> userTOs = prepareUserTOList();

		when(conversionService.convert(consumers.get(0), UserTO.class)).thenReturn(userTOs.get(0));
		when(conversionService.convert(consumers.get(1), UserTO.class)).thenReturn(userTOs.get(1));
		when(userRepository.findAll()).thenReturn(consumers);


		List<UserTO> retrievedListOfUsers = userService.list();

		verify(conversionService, times(1)).convert(consumers.get(0), UserTO.class);
		verify(conversionService, times(1)).convert(consumers.get(1), UserTO.class);
		verify(userRepository, times(1)).findAll();
		Assert.assertEquals(retrievedListOfUsers.size(), 2);

	}

	@Test
	public void GivenEmailWhenFindByEmailCalledThenShouldReturnUserWithThatEmail() {

		String email = "example@email.com";

		Consumer consumer = new Consumer();
		consumer.setEmail(email);

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(consumer));

		userService.findUserByEmail(email);

		Assert.assertEquals(consumer.getEmail(), email);

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
		Consumer consumer = prepareUser();
		
		when(conversionService.convert(userTO, Consumer.class)).thenReturn(consumer);
		when(userRepository.save(consumer)).thenReturn(consumer);
		when(conversionService.convert(consumer, UserTO.class)).thenReturn(userTO);

		userService.create(userTO);
		
	}

	@Test
	public void testUserUpdate() {

		UserTO userTO = prepareUserTo();
		userTO.setUserId("234");
		Consumer consumer = prepareUser();

		when(userRepository.findByUserId(userTO.getUserId())).thenReturn(Optional.of(consumer));
		when(conversionService.convert(userTO, Consumer.class)).thenReturn(consumer);
		when(userRepository.save(consumer)).thenReturn(consumer);
		when(conversionService.convert(consumer, UserTO.class)).thenReturn(userTO);

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


	private Consumer prepareUser() {
		Consumer consumer = new Consumer();
		consumer.setFirstName("first name");
		consumer.setLastName("last name");
		consumer.setEmail("example@email.com");
		consumer.setPassword("myPass");
		return consumer;
	}

	private List<Consumer> prepareUserList() {

		List<Consumer> consumers = new ArrayList<Consumer>() {
			{
				add(new Consumer());
				add(new Consumer());
			}
		};
		return consumers;
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
