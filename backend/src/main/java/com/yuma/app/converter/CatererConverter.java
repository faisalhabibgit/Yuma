package com.yuma.app.converter;

import org.springframework.core.convert.converter.Converter;

import com.yuma.app.document.Caterer;
import com.yuma.app.to.CatererTO;

public class CatererConverter implements Converter<CatererTO, Caterer> {
	
	@Override
	public Caterer convert(CatererTO catererTo) {
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
