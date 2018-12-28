package com.yuma.app.document;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QIngredients is a Querydsl query type for Ingredients
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIngredients extends EntityPathBase<Ingredients> {

	public static final QIngredients ingredients = new QIngredients("ingredients");
	private static final long serialVersionUID = -1324781363L;
	public final NumberPath<Double> calories = createNumber("calories", Double.class);

	public final StringPath name = createString("name");

	public final NumberPath<Double> price = createNumber("price", Double.class);

	public final NumberPath<Double> weight = createNumber("weight", Double.class);

	public QIngredients(String variable) {
		super(Ingredients.class, forVariable(variable));
	}

	public QIngredients(Path<? extends Ingredients> path) {
		super(path.getType(), path.getMetadata());
	}

	public QIngredients(PathMetadata metadata) {
		super(Ingredients.class, metadata);
	}

}

