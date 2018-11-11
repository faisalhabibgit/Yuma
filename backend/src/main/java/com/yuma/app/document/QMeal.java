package com.yuma.app.document;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import java.util.UUID;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QMeal is a Querydsl query type for Meal
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMeal extends EntityPathBase<Meal> {

	public static final QMeal meal = new QMeal("meal");
	private static final long serialVersionUID = 1646236184L;
	public final StringPath description = createString("description");

	public final ListPath<Ingredients, QIngredients> ingredients = this.<Ingredients, QIngredients>createList("ingredients", Ingredients.class, QIngredients.class, PathInits.DIRECT2);

	public final BooleanPath isAvailable = createBoolean("isAvailable");

	public final ComparablePath<UUID> mealId = createComparable("mealId", java.util.UUID.class);

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

