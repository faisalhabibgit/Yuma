package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.User;
import com.yuma.app.to.UserTO;

public class UserConverter implements Converter<UserTO, User>{

	@Override
	public User convert(UserTO userTO) {
		User user = new User();
		user.setFirstName(userTO.getFirstName());
		user.setLastName(userTO.getLastName());
		user.setEmail(userTO.getEmail());
		user.setPreferences(userTO.getPreferences());
		user.setEnabled(userTO.isEnabled());
		user.setTimestamp(userTO.getTimestamp());
		user.setRoles(userTO.getRoles());
		user.setUserId(userTO.getUserId());
		return user;
	}
}
