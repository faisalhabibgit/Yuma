package com.yuma.app.util;

import com.yuma.app.document.Meal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Helper {

	public static List<Meal> toList(final Iterable<com.yuma.app.document.Meal> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false)
			.collect(Collectors.toList());
	}
}