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

import com.yuma.app.to.UserTO;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@InjectMocks
	UserController userController;

	@Mock
	ConsumerService userService;

	@Test
	public void getAllTest() {
		when(userService.list()).thenReturn(new ArrayList<UserTO>());
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
		UserTO userTO = prepareAndReturnUserTo();
		when(userService.create(userTO)).thenReturn(userTO);
		userController.createUser(userTO);
		verify(userService).create(userTO);
	}

	@Test
	public void testUpdateUser() {
		UserTO userTO = prepareAndReturnUserTo();
		when(userService.updateUser(userTO)).thenReturn(userTO);
		userController.updateUser(userTO);
		verify(userService).updateUser(userTO);
	}
	
	@Test
	public void testGetByCompany(){
		
		ArrayList<UserTO> actualList = new ArrayList<UserTO>(Arrays.asList(Mockito.mock(UserTO.class),Mockito.mock(UserTO.class)));
		
		when(userService.findUsersByCompany("Google")).thenReturn(actualList);
		
		//call our API
		userController.getUsersByCompany("Google");
		verify(userService).findUsersByCompany("Google");
		
	}
	
	@Test
	public void testGetCompanyDoesntExist(){

		List<UserTO> emptyList;
		when(userService.existsByCompany("AGreatcompany")).thenReturn((Boolean.FALSE));
		emptyList = userController.getUsersByCompany("AGreatcompany");
		Assert.assertTrue(emptyList.size() == 0);
		
	}

	private UserTO prepareAndReturnUserTo() {
		UserTO userTO = new UserTO();
		return userTO;
	}
}
