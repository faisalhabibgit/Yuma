package com.yuma.app.to;

import com.yuma.app.document.Address;
import com.yuma.app.document.Meal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatererTo {

	private UUID userId;
	private String name;
	private String email;
	private Address address;
	private String specialty;
	private ArrayList<Meal> meals;
	private String timestamp;

	@Override
	public String toString() {
		return "CatererTo{" +
			"userId=" + userId +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", address=" + address +
			", specialty='" + specialty + '\'' +
			", meals=" + meals +
			", timestamp='" + timestamp + '\'' +
			'}';
	}
}
