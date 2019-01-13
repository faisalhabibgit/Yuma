package com.yuma.app.document;

import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
	private int numOfMeals;
	private boolean extraVeggies;
	private boolean extraProtein;
	private HashSet<String> details;
}
