package com.yuma.app.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Address {

	private int streetAddress;
	private String street;
	private String city;
	private String state;
	private String country;
	private String postalCode;


	@Override
	public String toString() {
		return "Address{" +
			"street address=" + streetAddress +
			", street name=" + street + '\'' +
			", city=" + city + '\'' +
			", state=" + state + '\'' +
			", country=" + country + '\'' +
			", postalCode=" + postalCode +
			'}';

	}

}
