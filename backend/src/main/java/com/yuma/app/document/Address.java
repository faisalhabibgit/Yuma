package com.yuma.app.document;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter

public class Address {

    private final int streetAddress;
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final String postalCode;

    public Address(int streetAddress, String street,String city,String state,String country,String postalCode){

        this.streetAddress = streetAddress;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;

    }

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
