package com.yuma.app.document;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

	public static final QAddress address = new QAddress("address");
	private static final long serialVersionUID = 999883135L;
	public final StringPath city = createString("city");

	public final StringPath country = createString("country");

	public final StringPath postalCode = createString("postalCode");

	public final StringPath state = createString("state");

	public final StringPath street = createString("street");

	public final NumberPath<Integer> streetAddress = createNumber("streetAddress", Integer.class);

	public QAddress(String variable) {
		super(Address.class, forVariable(variable));
	}

	public QAddress(Path<? extends Address> path) {
		super(path.getType(), path.getMetadata());
	}

	public QAddress(PathMetadata metadata) {
		super(Address.class, metadata);
	}

}

