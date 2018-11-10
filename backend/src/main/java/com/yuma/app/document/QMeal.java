package com.yuma.app.document;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QMeal is a Querydsl query type for Meal
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMeal extends EntityPathBase<Meal> {

	private static final long serialVersionUID = 1646236184L;

	public static final QMeal meal = new QMeal("meal");

	public final StringPath description = createString("description");

	public final ListPath<Ingredients, QIngredients> ingredients = this.<Ingredients, QIngredients>createList("ingredients", Ingredients.class, QIngredients.class, PathInits.DIRECT2);

	public final BooleanPath isAvailable = createBoolean("isAvailable");

	public final ComparablePath<java.util.UUID> mealId = createComparable("mealId", java.util.UUID.class);

	public QMeal(String variable) {
		super(Meal.class, forVariable(variable));
	}

	public QMeal(Path<? extends Meal> path) {
		super(path.getType(), path.getMetadata());
	}

	public QMeal(PathMetadata metadata) {
		super(Meal.class, metadata);
	}

}

