package com.yuma.app.converter;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.ConsumerTO;

public class ConsumerTOConverterTest {

	@Test
	public void testUserTOConverter() {
		Consumer consumer = prepareUser();
		UserTOConverter userTOConverter = new UserTOConverter();
		ConsumerTO consumerTo = userTOConverter.convert(consumer);
		
		Assert.assertEquals(consumerTo.getUserId(), consumer.getUserId());
		Assert.assertEquals(consumerTo.getFirstName(), consumer.getFirstName());
		Assert.assertEquals(consumerTo.getLastName(), consumer.getLastName());
		Assert.assertEquals(consumerTo.isEnabled(), consumer.isActive());
	}

	public Consumer prepareUser() {
		Consumer consumer = new Consumer();
		consumer.setEmail("test@gmail.com");
		consumer.setUserId("id");
		consumer.setActive(true);
		consumer.setFirstName("first name");
		consumer.setLastName("last name");
		return consumer;
	}
}
