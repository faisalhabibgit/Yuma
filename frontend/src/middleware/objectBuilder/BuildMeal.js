import React, { Component } from 'react';

import Meal from '../objects/Meal';
import Ingredients from '../objects/Ingredients';

class BuildMeal extends Component {
    constructor() {
        super()

        fetch('api/meals')
        .then(function(response) {
        return response.json();
        })
        .then(function(myJson) {
        //console.log(JSON.stringify(myJson));
        
        for(var i = 0; i < myJson.length; i++) {
            var obj = myJson[i];
            console.log("Meal ID: " + obj.mealId);
            console.log("Description: " + obj.description);
            console.log("Flags: " + obj.flags);
            console.log("Any left?: " + obj.available);
            
            for (var j in obj.ingredients) {

                var ingredientObj = obj.ingredients[j];
                
                console.log("Name: " + ingredientObj.name);
                console.log("Weight: " + ingredientObj.weight);
                console.log("Calories: " + ingredientObj.calories);
                console.log("Price: " + ingredientObj.price);
            }
        }

        });
    }

    render() {
        
        return (
            <div>
                <h1>Building Meal...</h1>
            </div>
        );
      }
}

export default BuildMeal;