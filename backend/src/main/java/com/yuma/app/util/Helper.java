package com.yuma.app.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.yuma.app.document.User;
import com.yuma.app.document.Meal;

public class Helper {

	public static List<Meal> toMealList(final Iterable<com.yuma.app.document.Meal> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
	}
	
	public static List<User> toUserList(final Iterable<User> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
	}
}
