package com.yuma.app.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.yuma.app.service.UserServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yuma.app.to.UserTO;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImp userService;

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

	private UserTO prepareAndReturnUserTo() {
		UserTO userTO = new UserTO();
		return userTO;
	}
}
