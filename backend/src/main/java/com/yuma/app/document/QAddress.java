package com.yuma.app.document;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.yuma.app.document.Address;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

	private static final long serialVersionUID = 999883135L;

	public static final QAddress address = new QAddress("address");

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

