package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.User;
import com.yuma.app.to.UserTO;

public class UserConverter implements Converter<UserTO, User>{

	@Override
	public User convert(UserTO consumerTO) {
		User consumer = new User();
		consumer.setFirstName(consumerTO.getFirstName());
		consumer.setLastName(consumerTO.getLastName());
		consumer.setEmail(consumerTO.getPersonalEmail());
		consumer.setPreferences(consumerTO.getPreferences());
		consumer.setTimestamp(consumerTO.getTimestamp());
		consumer.setUserId(consumerTO.getUserId());
		return consumer;
	}
}
