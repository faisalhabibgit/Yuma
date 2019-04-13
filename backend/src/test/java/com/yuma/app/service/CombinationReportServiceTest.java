package com.yuma.app.service;

import com.yuma.app.HelperCombo;
import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Meal;
import com.yuma.app.exception.ResourceNotFoundException;
import com.yuma.app.repository.CombinationReportRepository;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.service.HelperCombo.CombinationReportHelper;
import com.yuma.app.service.HelperCombo.WeeklyCombinationHelper;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.yuma.app.HelperCombo.prepareMealList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CombinationReportServiceTest {

	@InjectMocks
	private CombinationReportService combinationReportService;

	@Mock
	private CombinationReportHelper combinationReportHelper;

	@Mock
	private ConversionService conversionService;

	@Mock
	private WeeklyCombinationHelper weeklyCombinationHelper;

	@Mock
	private MealRepository mealRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private CombinationReportRepository combinationReportRepository;

	@Mock
	private List<Meal> mealList;

	@Mock
	private List<Consumer> userList;

	private CombinationReport combinationReport;

	private List<CombinationReport> possibleCombinations;

	private Optional<CombinationReport> optionalCombinationReport;

	private Optional<CombinationReport> optionalCombinationReportEmpty;

	@Before
	public void setup() {

		mealList = prepareMealList();
		userList = HelperCombo.prepareUserList();
		combinationReportHelper = new CombinationReportHelper();
		possibleCombinations = new ArrayList<>();
		combinationReport = HelperCombo.prepareAndReturnCombinationReport();
		possibleCombinations.add(combinationReport);
		combinationReportService = new CombinationReportService(conversionService);
		optionalCombinationReport = Optional.of(combinationReport);
		optionalCombinationReportEmpty = Optional.empty();

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void getCombinationReportByDateTest(){
		when(combinationReportRepository.findCombinationReportByCreatedOnBetween(any(Date.class), any(Date.class))).thenReturn(optionalCombinationReport);
		combinationReportService.getCombinationReportByDate(new DateTime());
		verify(combinationReportRepository, times(1)).findCombinationReportByCreatedOnBetween(any(Date.class), any(Date.class));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void getCombinationReportByDateNotFoundTest(){
		when(combinationReportRepository.findCombinationReportByCreatedOnBetween(any(Date.class), any(Date.class))).thenReturn(optionalCombinationReportEmpty);
		combinationReportService.getCombinationReportByDate(new DateTime());
	}

	@Test
	public void getMostRecentlyAddedTest(){
		when(combinationReportRepository.findTopByOrderByCreatedOnDesc()).thenReturn(optionalCombinationReport);
		combinationReportService.getMostRecentlyAdded();
		verify(combinationReportRepository, times(1)).findTopByOrderByCreatedOnDesc();
	}

	@Test(expected = ResourceNotFoundException.class)
	public void getMostRecentlyAddedNotFoundTest(){
		when(combinationReportRepository.findTopByOrderByCreatedOnDesc()).thenReturn(optionalCombinationReportEmpty);
		combinationReportService.getMostRecentlyAdded();
	}

	@Test
	public void generateWeeklyCombinationTest(){
		when(mealRepository.findByIsAvailableIsTrue()).thenReturn(mealList);
		when(userRepository.findByIsActiveIsTrue()).thenReturn(userList);

		combinationReportService.generateWeeklyCombination();

		verify(mealRepository, times(1)).findByIsAvailableIsTrue();
		verify(userRepository, times(1)).findByIsActiveIsTrue();
	}
}
