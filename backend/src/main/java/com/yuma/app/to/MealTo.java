package com.yuma.app.to;

import com.yuma.app.document.Ingredients;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealTo {

    private UUID mealId;
    private String description;
    private boolean isAvailable;
    protected List<Ingredients> ingredients;

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                ", ingredients=" + ingredients +
                '}';
    }

}
