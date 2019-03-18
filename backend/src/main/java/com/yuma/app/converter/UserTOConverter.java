package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Consumer;
import com.yuma.app.to.UserTO;

public class UserTOConverter implements Converter<Consumer, UserTO> {

	@Override
	public UserTO convert(Consumer consumer) {
		UserTO userTO = new UserTO();
		userTO.setFirstName(consumer.getFirstName());
		userTO.setLastName(consumer.getLastName());
		userTO.setEmail(consumer.getEmail());
		userTO.setPlan(consumer.getPlan());
		userTO.setEnabled(consumer.isActive());
		userTO.setTimestamp(consumer.getTimestamp());
		userTO.setCompany(consumer.getCompany());
		userTO.setUserId(consumer.getUserId());
		userTO.setMealList(consumer.getMealList());
		userTO.setDislikesList(consumer.getDislikesList());
		return userTO;
	}
}
