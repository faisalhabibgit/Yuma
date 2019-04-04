package com.yuma.app.util;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Meal;
import com.yuma.app.document.WeeklyCombination;
import com.yuma.app.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WeeklyCombinationHelper {

	private CombinationReportHelper combinationReportHelper;
	@Autowired
	private MealRepository mealRepository;
	public  void runFirstMealCombinationAlgorithm(WeeklyCombination weeklyCombination, List<Meal> addedMeals) {
		combinationReportHelper.setMealScores(weeklyCombination.getAvailableMeals(), weeklyCombination.getActiveUsers());
		weeklyCombination.setCombinationReport( new CombinationReport(0, combinationReportHelper.countCombinationScore(weeklyCombination.getAvailableMeals()), weeklyCombination.getActiveUsers(), weeklyCombination.getAvailableMeals()));
		combinationReportHelper.runMealCombinationAlgorithm(weeklyCombination, addedMeals);
		weeklyCombination.getCombinationReport().getMealsList().addAll(addedMeals);

		}
	public  void reRunMealCombinationAlgorithm(WeeklyCombination weeklyCombination, int i, List<Meal> addedMeals){
		List<Meal> highlyRankedMeals = mealRepository.findTop3ByOrderByMealScoreDesc();
		weeklyCombination.setCombinationReport( new CombinationReport(0, combinationReportHelper.countCombinationScore(weeklyCombination.getAvailableMeals()), weeklyCombination.getActiveUsers(), weeklyCombination.getAvailableMeals()));
		combinationReportHelper.replaceLowestScore(weeklyCombination.getCombinationReport(), i, highlyRankedMeals);
		combinationReportHelper.runMealCombinationAlgorithm(weeklyCombination, addedMeals);
		weeklyCombination.getCombinationReport().getMealsList().addAll(addedMeals);
		i++;
	}
}
