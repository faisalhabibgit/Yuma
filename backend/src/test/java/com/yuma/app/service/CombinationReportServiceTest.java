//package com.yuma.app.service;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import com.yuma.app.HelperCombo;
//import com.yuma.app.util.CombinationReportHelper;
//import com.yuma.app.util.WeeklyCombinationHelper;
//import org.joda.time.DateTime;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.ConversionService;
//
//import com.yuma.app.document.CombinationReport;
//import com.yuma.app.document.Consumer;
//import com.yuma.app.document.Ingredients;
//import com.yuma.app.document.Meal;
//import com.yuma.app.document.Plan;
//import com.yuma.app.repository.CombinationReportRepository;
//import com.yuma.app.repository.MealRepository;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class CombinationReportServiceTest {
//
//	@InjectMocks
//	private CombinationReportService combinationReportService;
//
//	@Mock
//	private CombinationReportHelper combinationReportHelper;
//
//	@Mock
//	private ConversionService conversionService;
//
//
//	@Mock
//	private WeeklyCombinationHelper weeklyCombinationHelper;
//
//	@Mock
//	private MealRepository mealRepository;
//
//	@Mock
//	private List<Meal> addedMeals;
//
//	private List<CombinationReport> possibleCombinations;
//
//	@Mock
//	private CombinationReportRepository combinationReportRepository;
//
//	private CombinationReport combinationReport;
//
//	@Mock
//	private List<Meal> mealList;
//	@Before
//	public void setup() {
//
////		combinationReportService = new CombinationReportService(conversionService);
//		mealList = HelperCombo.prepareMealList();
//		combinationReportHelper = new CombinationReportHelper();
//		possibleCombinations = new ArrayList<>();
//		combinationReport = new CombinationReport(0, 0, new ArrayList<>(), mealList);
//		possibleCombinations.add(combinationReport);
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	public void saveCombinationReportTest(){
//		combinationReportService.saveCombinationReport(1);
//		verify(combinationReportRepository, times(1)).save(any(CombinationReport.class));
//
//	}
//}
