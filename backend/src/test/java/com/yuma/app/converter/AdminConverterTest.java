package com.yuma.app.converter;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.UserTO;

public class AdminConverterTest {
	@Test
	public void testUserConverter() {
		UserTO userTo = prepareUserTO();
		UserConverter userConverter = new UserConverter();
		Consumer consumer = userConverter.convert(userTo);

		Assert.assertEquals(consumer.getUserId(), userTo.getUserId());
		Assert.assertEquals(consumer.getFirstName(), userTo.getFirstName());
		Assert.assertEquals(consumer.getLastName(), userTo.getLastName());
		Assert.assertEquals(consumer.isActive(), userTo.isEnabled());
		Assert.assertEquals(consumer.getPassword(), userTo.getPassword());
	}

	private UserTO prepareUserTO() {
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
