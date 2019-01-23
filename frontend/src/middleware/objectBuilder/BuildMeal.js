import Meal from '../objects/Meal';
import Ingredients from '../objects/Ingredients';
import ApiToken from '../ApiToken';


class BuildMeal {

    constructor() {
        console.log("Building Meal...");
    }

    getMealPromiseObj() {
        var mealList = [];
        var meal;
        var ingredientsList = [];
        var ingredients;

        const apiToken = new ApiToken();

        var obj = {
            method: 'GET',
            headers: {
                'cache-control': "no-cache",
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + apiToken.getCookie('yuma-token')
            }
        }

        return fetch('http://localhost:2020/api/meals/all', obj)
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Server response wasn\'t OK');
                }
            })
            .then((myJson) => {
                for (var i = 0; i < myJson.length; i++) {
                    var obj = myJson[i];

                    for (var j in obj.ingredients) {
                        var ingredientObj = obj.ingredients[j];

                        ingredients = new Ingredients(ingredientObj.name, ingredientObj.weight,
                            ingredientObj.calories, ingredientObj.price);
                        ingredientsList.push(ingredients);
                    }

                    meal = new Meal(obj.mealId, obj.description, obj.available,
                        obj.flags, ingredientsList);
                    mealList.push(meal);
                    ingredientsList = [];
                }
                return mealList;
            });
    }

    addMeal(someMeal) {

        const apiToken = new ApiToken();
        const API = 'api/meals';

        console.log('Adding: ' + someMeal.toString());

        fetch(API, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + apiToken.getCookie('yuma-token')
            },
            body: JSON.stringify({
                ingredients: someMeal.ingredients,
                name: someMeal.name,
                description: someMeal.description,
                flags: someMeal.flags,
                available: true
            })
        })

    }

}

export default BuildMeal;
