package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.ConsumerTO;

public class ConsumerTOConverter implements Converter<Consumer,ConsumerTO> {

	@Override
	public ConsumerTO convert(Consumer consumer) {
		ConsumerTO consumerTO = new ConsumerTO();
		consumerTO.setFirstName(consumer.getFirstName());
		consumerTO.setLastName(consumer.getLastName());
		consumerTO.setPersonalEmail(consumer.getPersonalEmail());
		consumerTO.setWorkEmail(consumer.getWorkEmail());
		consumerTO.setPreferences(consumer.getPreferences());
		consumerTO.setTimestamp(consumer.getTimestamp());
		consumerTO.setUserId(consumer.getUserId());
		return null;
	}
}
