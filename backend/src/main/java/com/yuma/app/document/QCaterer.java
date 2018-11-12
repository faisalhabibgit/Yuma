package com.yuma.app.document;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QCaterer is a Querydsl query type for Caterer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCaterer extends EntityPathBase<Caterer> {

	public static final QCaterer caterer = new QCaterer("caterer");
	private static final long serialVersionUID = -1591563141L;
	private static final PathInits INITS = PathInits.DIRECT2;
	public final QAddress address;

	public final StringPath email = createString("email");

	public final ListPath<Meal, QMeal> meals = this.<Meal, QMeal>createList("meals", Meal.class, QMeal.class, PathInits.DIRECT2);

	public final StringPath name = createString("name");

	public final StringPath specialty = createString("specialty");

	public final StringPath timestamp = createString("timestamp");

	public final ComparablePath<java.util.UUID> userId = createComparable("userId", java.util.UUID.class);

	public QCaterer(String variable) {
		this(Caterer.class, forVariable(variable), INITS);
	}

	public QCaterer(Path<? extends Caterer> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QCaterer(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QCaterer(PathMetadata metadata, PathInits inits) {
		this(Caterer.class, metadata, inits);
	}

	public QCaterer(Class<? extends Caterer> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
	}

}

