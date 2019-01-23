package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.User;
import com.yuma.app.to.UserTO;

public class UserConverter implements Converter<UserTO, User> {

	@Override
	public User convert(UserTO userTO) {
		User user = new User();
		user.setFirstName(userTO.getFirstName());
		user.setLastName(userTO.getLastName());
		user.setPassword(userTO.getPassword());
		user.setEmail(userTO.getEmail());
		user.setPlan(userTO.getPlan());
		user.setActive(userTO.isEnabled());
		user.setTimestamp(userTO.getTimestamp());
		user.setRoles(userTO.getRoles());
		user.setUserId(userTO.getUserId());
		user.setMealList(userTO.getMealList());
		user.setDislikesList(userTO.getDislikesList());
		return user;
	}
}
