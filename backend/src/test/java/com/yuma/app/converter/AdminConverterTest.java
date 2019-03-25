package com.yuma.app.converter;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.ConsumerTO;

public class AdminConverterTest {
	@Test
	public void testUserConverter() {
		ConsumerTO consumerTo = prepareUserTO();
		UserConverter userConverter = new UserConverter();
		Consumer consumer = userConverter.convert(consumerTo);

		Assert.assertEquals(consumer.getUserId(), consumerTo.getUserId());
		Assert.assertEquals(consumer.getFirstName(), consumerTo.getFirstName());
		Assert.assertEquals(consumer.getLastName(), consumerTo.getLastName());
		Assert.assertEquals(consumer.isActive(), consumerTo.isEnabled());
		Assert.assertEquals(consumer.getPassword(), consumerTo.getPassword());
	}

	private ConsumerTO prepareUserTO() {
		ConsumerTO consumerTO = new ConsumerTO();
		consumerTO.setUserId("id");
		consumerTO.setFirstName("first name");
		consumerTO.setLastName("last name");
		consumerTO.setEnabled(true);
		consumerTO.setEmail("test@email.com");
		return consumerTO;
	}
}
