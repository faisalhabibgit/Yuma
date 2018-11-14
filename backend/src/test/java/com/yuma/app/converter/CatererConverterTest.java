package com.yuma.app.converter;

import com.yuma.app.document.Address;
import com.yuma.app.document.Caterer;
import com.yuma.app.to.CatererTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

public class CatererConverterTest {

	@Test
	public void testCatererConverter() {

		CatererTO catererTO = prepareCatererTO();

		CatererConverter catererConverter = new CatererConverter();

		Caterer caterer = catererConverter.convert(catererTO);

		Assert.assertEquals(caterer.getUserId(), catererTO.getUserId());
		Assert.assertEquals(caterer.getAddress(), catererTO.getAddress());
		Assert.assertEquals(caterer.getEmail(), catererTO.getEmail());
		Assert.assertEquals(caterer.getName(), catererTO.getName());		
		Assert.assertEquals(caterer.getSpecialty(), catererTO.getSpecialty());
		Assert.assertEquals(caterer.getMeals(), catererTO.getMeals());
		
	}

	public CatererTO prepareCatererTO() {
		CatererTO catererTO = new CatererTO();
		catererTO.setUserId(UUID.randomUUID());
		catererTO.setName("JohnSmith");
		catererTO.setEmail("johnsmith@gmail.com");
		catererTO.setSpecialty("mediterranean");
		catererTO.setTimestamp("20180912");
		catererTO.setAddress(new Address(5601, "smart", "CSL", "QC", "Canada", "H4W2m4"));
		catererTO.setMeals(new ArrayList<>());
	
		return catererTO;
	}
}
