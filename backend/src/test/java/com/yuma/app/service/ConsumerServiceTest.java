package com.yuma.app.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.yuma.app.to.ConsumerTO;

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
	private ConsumerService userService;
	
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
		List<ConsumerTO> consumerTOS = prepareUserTOList();

		when(conversionService.convert(consumers.get(0), ConsumerTO.class)).thenReturn(consumerTOS.get(0));
		when(conversionService.convert(consumers.get(1), ConsumerTO.class)).thenReturn(consumerTOS.get(1));
		when(userRepository.findAll()).thenReturn(consumers);


		List<ConsumerTO> retrievedListOfUsers = userService.list();

		verify(conversionService, times(1)).convert(consumers.get(0), ConsumerTO.class);
		verify(conversionService, times(1)).convert(consumers.get(1), ConsumerTO.class);
		verify(userRepository, times(1)).findAll();
		Assert.assertEquals(retrievedListOfUsers.size(), 2);

	}

	@Test
	public void WhenListUserCompaniesCalledThenShouldReturnListOfUsersCompanies() {

		List<Consumer> consumers = new ArrayList<>();

		Consumer consumer1 = new Consumer();
		Consumer consumer2 = new Consumer();
		consumer1.setCompany("Concordia");
		consumer2.setCompany("Mcgill");
		
		consumers.add(consumer1);
		consumers.add(consumer2);
		
		when(userRepository.findAll()).thenReturn(consumers);
		
		Set<String> companies = userService.listUserCompanies();
		
		verify(userRepository).findAll();
		Assert.assertEquals(companies.size(), 2);

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

		ConsumerTO consumerTO = prepareUserTo();
		Consumer consumer = prepareUser();
		
		when(conversionService.convert(consumerTO, Consumer.class)).thenReturn(consumer);
		when(userRepository.save(consumer)).thenReturn(consumer);
		when(conversionService.convert(consumer, ConsumerTO.class)).thenReturn(consumerTO);

		userService.create(consumerTO);
		
	}

	@Test
	public void testUserUpdate() {

		ConsumerTO consumerTO = prepareUserTo();
		consumerTO.setUserId("234");
		Consumer consumer = prepareUser();

		when(userRepository.findByUserId(consumerTO.getUserId())).thenReturn(Optional.of(consumer));
		when(conversionService.convert(consumerTO, Consumer.class)).thenReturn(consumer);
		when(userRepository.save(consumer)).thenReturn(consumer);
		when(conversionService.convert(consumer, ConsumerTO.class)).thenReturn(consumerTO);

		userService.updateUser(consumerTO);

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

	private List<ConsumerTO> prepareUserTOList() {

		List<ConsumerTO> consumerTOS = new ArrayList<ConsumerTO>() {
			{
				add(new ConsumerTO());
				add(new ConsumerTO());
			}
		};
		return consumerTOS;
	}
	
	private ConsumerTO prepareUserTo(){
		
		ConsumerTO consumerTO = new ConsumerTO();
		return consumerTO;
	}
}
