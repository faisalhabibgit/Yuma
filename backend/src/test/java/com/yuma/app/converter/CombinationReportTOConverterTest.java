package com.yuma.app.converter;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Meal;
import com.yuma.app.document.User;
import com.yuma.app.to.CombinationReportTO;
import com.yuma.app.to.MealTO;
import com.yuma.app.to.UserTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;



@RunWith(MockitoJUnitRunner.class)
public class CombinationReportTOConverterTest {
	
	@Mock
	ConversionService conversionService;

	@Test
	public void testMealToConverter() {
		
		CombinationReport combinationReport = prepareCombinationReport();
		
		CombinationReportTOConverter combinationReportTOConverter = new CombinationReportTOConverter(conversionService);
		
		CombinationReportTO combinationReportTO = combinationReportTOConverter.convert(combinationReport);
		
		Assert.assertEquals(combinationReportTO.getId(), combinationReportTO.getId());
		Assert.assertEquals(combinationReportTO.getCombinationScore(), combinationReportTO.getCombinationScore());
		Assert.assertEquals(combinationReportTO.getNumberOfBlanks(), combinationReportTO.getNumberOfBlanks());


		verify(conversionService).convert(combinationReport.getUserList().get(0), UserTO.class);
		verify(conversionService).convert(combinationReport.getUserList().get(1), UserTO.class);
		
		verify(conversionService).convert(combinationReport.getMealsList().get(0), MealTO.class);
		verify(conversionService).convert(combinationReport.getMealsList().get(1), MealTO.class);
		
	}

	private CombinationReport prepareCombinationReport() {

		CombinationReport combinationReport = new CombinationReport();
		
		combinationReport.setId("123");
		combinationReport.setCombinationScore(123);
		combinationReport.setNumberOfBlanks(10);
		combinationReport.setMealsList(prepareMealList());
		combinationReport.setUserList(prepareUserList());
		
		return combinationReport;
	}
	
	private List<Meal> prepareMealList() {
		
		List<Meal> meals = new ArrayList<>();
		
		Meal meal1 = new Meal();
		meal1.setName("this is meal number 1");
		Meal meal2 = new Meal();
		meal2.setName("this is meal number 2");
		
		
		meals.add(meal1);
		meals.add(meal2);

		return meals;
	}

	private List<User> prepareUserList() {
		
		List<User> users = new ArrayList<>();

		User user1 = new User();
		user1.setFirstName("user1 first name");
		User user2 = new User();
		user2.setLastName("user2 first name");


		users.add(user1);
		users.add(user2);

		return users;
	}
}
