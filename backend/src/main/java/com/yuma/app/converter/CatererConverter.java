package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Caterer;
import com.yuma.app.to.CatererTo;

public class CatererConverter implements Converter<CatererTo, Caterer> {
	
	@Override
	public Caterer convert(CatererTo catererTo) {
		Caterer caterer = new Caterer();
		caterer.setName(catererTo.getName());
		caterer.setAddress(catererTo.getAddress());
		caterer.setEmail(catererTo.getEmail());
		caterer.setMeals(catererTo.getMeals());
		caterer.setSpecialty(catererTo.getSpecialty());
		caterer.setTimestamp(catererTo.getTimestamp());
		caterer.setUserId(catererTo.getUserId());
		return caterer;
	}
}
