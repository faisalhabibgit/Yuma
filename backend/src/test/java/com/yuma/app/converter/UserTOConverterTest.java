package com.yuma.app.converter;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.User;
import com.yuma.app.to.UserTO;

public class UserTOConverterTest {

	@Test
	public void testUserTOConverter() {
		User user = prepareUser();
		UserTOConverter userTOConverter = new UserTOConverter();
		UserTO userTo = userTOConverter.convert(user);
		
		Assert.assertEquals(userTo.getUserId(), user.getUserId());
		Assert.assertEquals(userTo.getFirstName(), user.getFirstName());
		Assert.assertEquals(userTo.getLastName(), user.getLastName());
		Assert.assertEquals(userTo.getPassword(), user.getPassword());
		Assert.assertEquals(userTo.isEnabled(), user.isEnabled());
	}

	public User prepareUser() {
		User user = new User();
		user.setEmail("test@gmail.com");
		user.setUserId("id");
		user.setEnabled(true);
		user.setFirstName("first name");
		user.setLastName("last name");
		user.setPassword("password");
		return user;
	}
}
