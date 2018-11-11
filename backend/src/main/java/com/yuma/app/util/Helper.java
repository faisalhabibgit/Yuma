package com.yuma.app.util;

import com.yuma.app.document.Caterer;
import com.yuma.app.document.Meal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.yuma.app.document.Meal;

public class Helper {

	public static List<Meal> toMealList(final Iterable<com.yuma.app.document.Meal> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
	}

	public static List<Caterer> toCatererList(final Iterable<com.yuma.app.document.Caterer> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
	}
}
