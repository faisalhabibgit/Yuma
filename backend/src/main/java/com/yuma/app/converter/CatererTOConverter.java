package com.yuma.app.converter;

import com.yuma.app.document.Caterer;
import com.yuma.app.to.CatererTO;
import org.springframework.core.convert.converter.Converter;

public class CatererTOConverter implements Converter<Caterer, CatererTO> {
	@Override
	public CatererTO convert(Caterer caterer) {
		CatererTO catererTo = new CatererTO();
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
