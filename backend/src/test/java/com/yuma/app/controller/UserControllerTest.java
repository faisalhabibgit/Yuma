package com.yuma.app.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yuma.app.service.ConsumerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yuma.app.to.ConsumerTO;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@InjectMocks
	UserController userController;

	@Mock
	ConsumerService userService;

	@Test
	public void getAllTest() {
		when(userService.list()).thenReturn(new ArrayList<ConsumerTO>());
		userController.getAll();
		verify(userService).list();
	}

	@Test
	public void testDeleteUserById() {
		String userId = "123";
		doNothing().when(userService).deleteUserByUserID(userId);
		userController.deleteUserByUserID(userId);
		verify(userService).deleteUserByUserID(userId);
	}

	@Test
	public void testCreateUser() {
		ConsumerTO consumerTO = prepareAndReturnUserTo();
		when(userService.create(consumerTO)).thenReturn(consumerTO);
		userController.createUser(consumerTO);
		verify(userService).create(consumerTO);
	}

	@Test
	public void testUpdateUser() {
		ConsumerTO consumerTO = prepareAndReturnUserTo();
		when(userService.updateUser(consumerTO)).thenReturn(consumerTO);
		userController.updateUser(consumerTO);
		verify(userService).updateUser(consumerTO);
	}
	
	@Test
	public void testGetByCompany(){
		
		ArrayList<ConsumerTO> actualList = new ArrayList<ConsumerTO>(Arrays.asList(Mockito.mock(ConsumerTO.class),Mockito.mock(ConsumerTO.class)));
		
		when(userService.findUsersByCompany("Google")).thenReturn(actualList);
		
		//call our API
		userController.getUsersByCompany("Google");
		verify(userService).findUsersByCompany("Google");
		
	}
	
	@Test
	public void testGetCompanyDoesntExist(){

		List<ConsumerTO> emptyList;
		when(userService.existsByCompany("AGreatcompany")).thenReturn((Boolean.FALSE));
		
		//to mock mongo, return an empty list
		if(userService.existsByCompany("AGreatcompany"))
			when(userController.getUsersByCompany("AGreatcompany")).thenReturn(new ArrayList<ConsumerTO>());
		
		emptyList = userController.getUsersByCompany("AGreatcompany");
		Assert.assertTrue(emptyList.size() == 0);
		
	}

	private ConsumerTO prepareAndReturnUserTo() {
		ConsumerTO consumerTO = new ConsumerTO();
		return consumerTO;
	}
}
