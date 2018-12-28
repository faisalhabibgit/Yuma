package com.yuma.app.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.yuma.app.document.Consumer;
import com.yuma.app.document.Meal;

public class Helper {

	public static List<Meal> toMealList(final Iterable<com.yuma.app.document.Meal> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
	}
	
	public static List<Consumer> toConsumerList(final Iterable<Consumer> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
	}
}
