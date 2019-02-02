package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.UserTO;

public class UserConverter implements Converter<UserTO, Consumer> {

	@Override
	public Consumer convert(UserTO userTO) {
		Consumer consumer = new Consumer();
		consumer.setFirstName(userTO.getFirstName());
		consumer.setLastName(userTO.getLastName());
		consumer.setPassword(userTO.getPassword());
		consumer.setEmail(userTO.getEmail());
		consumer.setPlan(userTO.getPlan());
		consumer.setActive(userTO.isEnabled());
		consumer.setTimestamp(userTO.getTimestamp());
		consumer.setRoles(userTO.getRoles());
		consumer.setUserId(userTO.getUserId());
		consumer.setMealList(userTO.getMealList());
		consumer.setDislikesList(userTO.getDislikesList());
		return consumer;
	}
}
