package com.yuma.app.resources;

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

import com.yuma.app.document.Preferences;
import com.yuma.app.document.User;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceTest {

	@MockBean
	UserRepository consumerRepository;
	
	@Mock
	private Logger logger;
	
	@Mock
	ConversionService conversionService;

	@Autowired
	private UserResource consumerResource;
	
	private User consumer1;
	private User consumer2;
	

	@Before
	public void setUp() throws Exception {

    //consumer1 = new User(UUID.randomUUID(), "Ahmad", "baiazid", "ahmad.lego@gmail.com", "ahmad.lego@work.com",new Preferences(2, true, false, new HashSet<>()), "20190807");
    consumer1 = new User(UUID.randomUUID(),"pasword","Ahmad", "baiazid", "ahmad.lego@gmail.com",new Preferences(2, true, false, new HashSet<>()), true,"20190807",new HashSet<>());
    
    consumer2 = new User(UUID.randomUUID(),"pasword2","Ahmad2", "baiazi2d", "ahmad.2lego@gmail.com",new Preferences(2, true, false, new HashSet<>()), true,"20190107",new HashSet<>());
    
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
