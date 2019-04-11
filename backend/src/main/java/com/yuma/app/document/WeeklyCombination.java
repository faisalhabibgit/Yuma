package com.yuma.app.document;

import com.yuma.app.document.CombinationReport;
import com.yuma.app.document.Consumer;
import com.yuma.app.document.Meal;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeeklyCombination {
	
	CombinationReport combinationReport;
	List<CombinationReport> possibleCombinations;
	List<Meal> availableMeals;
	List<Consumer> activeUsers;
}
