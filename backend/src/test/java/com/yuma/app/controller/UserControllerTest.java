package com.yuma.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuma.app.document.Plan;
import com.yuma.app.document.User;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@MockBean
	UserRepository consumerRepository;

	@Mock
	private Logger logger;

	@Mock
	ConversionService conversionService;

	@Autowired
	private UserController consumerResource;

	private User consumer1;
	private User consumer2;


	@Before
	public void setUp() throws Exception {

		Plan plan1 = new Plan(4,false,false, new HashSet<>());
		Plan plan2 = new Plan(4,false,false, new HashSet<>());
		List<String> dislikes1 = new ArrayList<>();
		List<String> dislikes2 = new ArrayList<>();
		
		consumer1 = new User(UUID.randomUUID().toString(),"ahmad", "baiazid", "ahmad.baiazid@hotmail.com", "pass1", true, plan1,dislikes1);
		consumer2 = new User(UUID.randomUUID().toString(),"ahmad2", "baiazid2", "ah.baiazid@hotmail.com", "pass1", true, plan2,dislikes2);
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllTest(){
		List<User> actualUsers = new ArrayList<>();
		actualUsers.add(consumer1);
		actualUsers.add(consumer2);

		Mockito.when(consumerRepository.findAll()).thenReturn(actualUsers);
		Mockito.when(conversionService.convert(consumer1, UserTO.class)).thenReturn(new UserTO());
		Mockito.when(conversionService.convert(consumer2, UserTO.class)).thenReturn(new UserTO());

		List<UserTO> expectedUsers = consumerResource.getAll();

		Assert.assertEquals(expectedUsers.size(),actualUsers.size());
		
	}

}
