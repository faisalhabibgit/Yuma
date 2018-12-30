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
		userTO.setPreferences(user.getPreferences());
		userTO.setEnabled(user.isEnabled());
		userTO.setTimestamp(user.getTimestamp());
		userTO.setRoles(user.getRoles());
		userTO.setUserId(user.getUserId());
		return userTO;
	}
}
