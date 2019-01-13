package com.yuma.app.converter;

import com.yuma.app.document.User;
import com.yuma.app.to.UserTO;
import org.junit.Assert;
import org.junit.Test;

public class UserConverterTest {


	@Test
	public void testUserConverter() {

		UserTO userTo = prepareUserTO();

		UserConverter userConverter = new UserConverter();

		User user = userConverter.convert(userTo);

		Assert.assertEquals(user.getUserId(), userTo.getUserId());
		Assert.assertEquals(user.getFirstName(), userTo.getFirstName());
		Assert.assertEquals(user.getLastName(), userTo.getLastName());
		Assert.assertEquals(user.isActive(), userTo.isEnabled());
		Assert.assertEquals(user.getPassword(), userTo.getPassword());

	}

	public UserTO prepareUserTO() {
		UserTO userTO = new UserTO();
		userTO.setUserId("id");
		userTO.setFirstName("first name");
		userTO.setLastName("last name");
		userTO.setPassword("password");
		userTO.setEnabled(true);
		userTO.setEmail("test@email.com");

		return userTO;
	}
}
