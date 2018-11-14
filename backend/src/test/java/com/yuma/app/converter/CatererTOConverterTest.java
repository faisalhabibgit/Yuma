package com.yuma.app.converter;

import com.yuma.app.document.Address;
import com.yuma.app.document.Caterer;
import com.yuma.app.to.CatererTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

public class CatererTOConverterTest {

	@Test
	public void testCatererConverter() {

		Caterer caterer = prepareCaterer();

		CatererTOConverter catererTOConverter = new CatererTOConverter();

		CatererTO catererTO = catererTOConverter.convert(caterer);

		Assert.assertEquals(catererTO.getUserId(), caterer.getUserId());
		Assert.assertEquals(catererTO.getAddress(), caterer.getAddress());
		Assert.assertEquals(catererTO.getEmail(), caterer.getEmail());
		Assert.assertEquals(catererTO.getName(), caterer.getName());
		Assert.assertEquals(catererTO.getSpecialty(), caterer.getSpecialty());
		Assert.assertEquals(catererTO.getMeals(), caterer.getMeals());

	}

	public Caterer prepareCaterer() {
		Caterer caterer = new Caterer();
		caterer.setUserId(UUID.randomUUID());
		caterer.setName("JohnSmith");
		caterer.setEmail("johnsmith@gmail.com");
		caterer.setSpecialty("mediterranean");
		caterer.setTimestamp("20180912");
		caterer.setAddress(new Address(5601, "smart", "CSL", "QC", "Canada", "H4W2m4"));
		caterer.setMeals(new ArrayList<>());

		return caterer;
	}
}
