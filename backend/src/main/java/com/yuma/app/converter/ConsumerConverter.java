package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.ConsumerTO;

public class ConsumerConverter implements Converter<ConsumerTO, Consumer>{

	@Override
	public Consumer convert(ConsumerTO consumerTO) {
		Consumer consumer = new Consumer();
		consumer.setFirstName(consumerTO.getFirstName());
		consumer.setLastName(consumerTO.getLastName());
		consumer.setPersonalEmail(consumerTO.getPersonalEmail());
		consumer.setWorkEmail(consumerTO.getWorkEmail());
		consumer.setPreferences(consumerTO.getPreferences());
		consumer.setTimestamp(consumerTO.getTimestamp());
		consumer.setUserId(consumerTO.getUserId());
		return consumer;
	}
}
