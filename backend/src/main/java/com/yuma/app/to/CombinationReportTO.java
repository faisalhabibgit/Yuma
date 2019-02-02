package com.yuma.app.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CombinationReportTO {

	@Id
	private String id;
	private int numberOfBlanks;
	private int combinationScore;
	private List<UserTO> userTOS;
	private List<MealTO> mealTOS;

	@Override
	public String toString() {
		return "CombinationReport{" +
			"numberOfBlanks=" + numberOfBlanks +
			", combinationScore=" + combinationScore +
			", userList=" + userTOS +
			", mealsList=" + mealTOS +
			'}';
	}
}
