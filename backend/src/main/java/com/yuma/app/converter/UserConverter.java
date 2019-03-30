package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.ConsumerTO;

public class UserConverter implements Converter<ConsumerTO, Consumer> {

	@Override
	public Consumer convert(ConsumerTO consumerTO) {
		Consumer consumer = new Consumer();
		consumer.setFirstName(consumerTO.getFirstName());
		consumer.setLastName(consumerTO.getLastName());
		consumer.setEmail(consumerTO.getEmail());
		consumer.setPlan(consumerTO.getPlan());
		consumer.setActive(consumerTO.isEnabled());
		consumer.setTimestamp(consumerTO.getTimestamp());
		consumer.setCompany(consumerTO.getCompany());
		consumer.setYumaServerId(consumerTO.getYumaServerId());
		consumer.setUserId(consumerTO.getUserId());
		consumer.setMealList(consumerTO.getMealList());
		consumer.setAllergies(consumerTO.getAllergies());
		consumer.setDislikesList(consumerTO.getDislikesList());
		consumer.setLikes(consumerTO.getLikes());
		return consumer;
	}
}
