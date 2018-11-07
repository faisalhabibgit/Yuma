package com.yuma.app.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@Getter
@Setter
public class Preferences {

    private ArrayList<String> diets;
    private ArrayList<String> allergies;
    private ArrayList<String> preferences;


    public Preferences(ArrayList<String> diets, ArrayList<String> allergies, ArrayList<String> preferences) {
        this.diets = diets;
        this.allergies = allergies;
        this.preferences = preferences;
    }

    public Preferences() {
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "diets=" + diets +
                ", allergies=" + allergies +
                ", preferences=" + preferences +
                '}';
    }
}
