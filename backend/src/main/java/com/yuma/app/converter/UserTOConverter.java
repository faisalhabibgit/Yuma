package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.User;
import com.yuma.app.to.UserTO;

public class UserTOConverter implements Converter<User,UserTO> {

	@Override
	public UserTO convert(User consumer) {
		UserTO consumerTO = new UserTO();
		consumerTO.setFirstName(consumer.getFirstName());
		consumerTO.setLastName(consumer.getLastName());
		consumerTO.setWorkEmail(consumer.getEmail());
		consumerTO.setPreferences(consumer.getPreferences());
		consumerTO.setTimestamp(consumer.getTimestamp());
		consumerTO.setUserId(consumer.getUserId());
		return null;
	}
}
