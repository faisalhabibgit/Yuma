package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.User;
import com.yuma.app.to.UserTO;

public class UserTOConverter implements Converter<User, UserTO> {

	@Override
	public UserTO convert(User user) {
		UserTO userTO = new UserTO();
		userTO.setFirstName(user.getFirstName());
		userTO.setLastName(user.getLastName());
		userTO.setEmail(user.getEmail());
		userTO.setPassword(user.getPassword());
		userTO.setPlan(user.getPlan());
		userTO.setEnabled(user.isActive());
		userTO.setTimestamp(user.getTimestamp());
		userTO.setRoles(user.getRoles());
		userTO.setUserId(user.getUserId());
		userTO.setMealList(user.getMealList());
		userTO.setDislikesList(user.getDislikesList());
		return userTO;
	}
}
