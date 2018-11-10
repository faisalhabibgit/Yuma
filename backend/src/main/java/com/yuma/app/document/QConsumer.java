package com.yuma.app.document;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConsumer is a Querydsl query type for Consumer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConsumer extends EntityPathBase<Consumer> {

	private static final long serialVersionUID = 1582550987L;

	private static final PathInits INITS = PathInits.DIRECT2;

	public static final QConsumer consumer = new QConsumer("consumer");

	public final StringPath firstName = createString("firstName");

	public final StringPath lastName = createString("lastName");

	public final StringPath personalEmail = createString("personalEmail");

	public final QPreferences preferences;

	public final StringPath timestamp = createString("timestamp");

	public final ComparablePath<java.util.UUID> userId = createComparable("userId", java.util.UUID.class);

	public final StringPath workEmail = createString("workEmail");

	public QConsumer(String variable) {
		this(Consumer.class, forVariable(variable), INITS);
	}

	public QConsumer(Path<? extends Consumer> path) {
		this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
	}

	public QConsumer(PathMetadata metadata) {
		this(metadata, PathInits.getFor(metadata, INITS));
	}

	public QConsumer(PathMetadata metadata, PathInits inits) {
		this(Consumer.class, metadata, inits);
	}

	public QConsumer(Class<? extends Consumer> type, PathMetadata metadata, PathInits inits) {
		super(type, metadata, inits);
		this.preferences = inits.isInitialized("preferences") ? new QPreferences(forProperty("preferences")) : null;
	}

}

