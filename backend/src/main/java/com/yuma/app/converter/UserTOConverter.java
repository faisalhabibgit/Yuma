package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.ConsumerTO;

public class UserTOConverter implements Converter<Consumer, ConsumerTO> {

	@Override
	public ConsumerTO convert(Consumer consumer) {
		ConsumerTO consumerTO = new ConsumerTO();
		consumerTO.setFirstName(consumer.getFirstName());
		consumerTO.setLastName(consumer.getLastName());
		consumerTO.setEmail(consumer.getEmail());
		consumerTO.setPlan(consumer.getPlan());
		consumerTO.setEnabled(consumer.isActive());
		consumerTO.setTimestamp(consumer.getTimestamp());
		consumerTO.setCompany(consumer.getCompany());
		consumerTO.setUserId(consumer.getUserId());
		consumerTO.setMealList(consumer.getMealList());
		consumerTO.setAllergies(consumer.getAllergies());
		return consumerTO;
	}
}
