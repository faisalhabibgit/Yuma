package com.yuma.app.service;
import com.yuma.app.document.Meal;
import com.yuma.app.repository.MealRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.to.MealTO;
import org.springframework.core.convert.ConversionService;
import java.util.List;
import java.util.UUID;

public interface MealServiceImp {


	public List<MealTO> list() {

	}

	public MealTO update(MealTO mealTo) {

	}

	public MealTO create(MealTO mealTo) {

	}

	public List<MealTO> weeklyCombo() {

	}

	public List<MealTO> availableMeals() {

	}

	public void deleteMeal(UUID mealId) {


	}

	public MealTO findByDescription(String description) {

	}

	protected List<MealTO> convertMealToMealTO(List<Meal> meals) {


	}


}
	

	


