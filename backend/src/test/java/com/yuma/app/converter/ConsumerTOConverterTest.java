package com.yuma.app.converter;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.UserTO;

public class ConsumerTOConverterTest {

	@Test
	public void testUserTOConverter() {
		Consumer consumer = prepareUser();
		UserTOConverter userTOConverter = new UserTOConverter();
		UserTO userTo = userTOConverter.convert(consumer);
		
		Assert.assertEquals(userTo.getUserId(), consumer.getUserId());
		Assert.assertEquals(userTo.getFirstName(), consumer.getFirstName());
		Assert.assertEquals(userTo.getLastName(), consumer.getLastName());
		Assert.assertEquals(userTo.isEnabled(), consumer.isActive());
	}

	public Consumer prepareUser() {
		Consumer consumer = new Consumer();
		consumer.setEmail("test@gmail.com");
		consumer.setUserId("id");
		consumer.setActive(true);
		consumer.setFirstName("first name");
		consumer.setLastName("last name");
		consumer.setPassword("password");
		return consumer;
	}
}
