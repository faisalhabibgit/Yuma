package com.yuma.app.document;

import java.util.UUID;
import java.util.ArrayList;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Caterer {

    @Id
    private  UUID userId;
    private  String name;
    private  String email;
    private Address address;
    private String specialty;
    private ArrayList<Meal> meals;
    private String timestamp;


    private Caterer(UUID userId,String name,String email,Address address,String specialty,ArrayList<Meal> meals,String timestamp)
    {
        this.userId = userId;
        this.name= name;
        this.email= email;
        this.address = address;
        this.specialty = specialty;
        this.meals = meals;
        this.timestamp = timestamp;

    }

    @Override
    public String toString() {
        return "Caterer{" +
                "userID=" + userId +
                ", name=" + name + '\'' +
                ", email=" + email + '\'' +
                ", address=" + address + '\'' +
                ", specialty=" + specialty+ '\'' +
                ", meals=" + meals +
                ", timestamp=" + timestamp +
                '}';

    }

}
