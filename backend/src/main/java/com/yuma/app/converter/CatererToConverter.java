package com.yuma.app.converter;

import com.yuma.app.document.Caterer;
import com.yuma.app.to.CatererTo;
import org.springframework.core.convert.converter.Converter;

public class CatererToConverter implements Converter<Caterer, CatererTo> {
	@Override
	public CatererTo convert(Caterer caterer) {
		CatererTo catererTo = new CatererTo();
		catererTo.setName(caterer.getName());
		catererTo.setAddress(caterer.getAddress());
		catererTo.setEmail(caterer.getEmail());
		catererTo.setMeals(caterer.getMeals());
		catererTo.setSpecialty(caterer.getSpecialty());
		catererTo.setTimestamp(caterer.getTimestamp());
		catererTo.setUserId(caterer.getUserId());
		return catererTo;
	}
}
