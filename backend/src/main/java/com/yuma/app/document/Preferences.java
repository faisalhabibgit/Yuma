package com.yuma.app.document;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Preferences {
	private int numOfMeals;
	private boolean extraVeggies;
	private boolean extraProtein;
	private HashMap<String, Boolean> preferences;

	public Preferences(int numOfMeals, boolean extraVeggies, boolean extraProtein, HashMap<String, Boolean> preferences) {
		this.numOfMeals = numOfMeals;
		this.extraVeggies = extraVeggies;
		this.extraProtein = extraProtein;
		this.preferences = preferences;
	}
}
