package com.yuma.app.document;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.MapPath;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QPreferences is a Querydsl query type for Preferences
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPreferences extends EntityPathBase<Preferences> {

	private static final long serialVersionUID = -1526167805L;

	public static final QPreferences preferences1 = new QPreferences("preferences1");

	public final BooleanPath extraProtein = createBoolean("extraProtein");

	public final BooleanPath extraVeggies = createBoolean("extraVeggies");

	public final NumberPath<Integer> numOfMeals = createNumber("numOfMeals", Integer.class);

	public final MapPath<String, Boolean, BooleanPath> preferences = this.<String, Boolean, BooleanPath>createMap("preferences", String.class, Boolean.class, BooleanPath.class);

	public QPreferences(String variable) {
		super(Preferences.class, forVariable(variable));
	}

	public QPreferences(Path<? extends Preferences> path) {
		super(path.getType(), path.getMetadata());
	}

	public QPreferences(PathMetadata metadata) {
		super(Preferences.class, metadata);
	}

}

