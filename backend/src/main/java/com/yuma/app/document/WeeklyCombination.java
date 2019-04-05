package com.yuma.app.document;

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
