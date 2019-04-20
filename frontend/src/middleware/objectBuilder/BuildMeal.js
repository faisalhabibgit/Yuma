import Meal from '../objects/Meal';
import Ingredients from '../objects/Ingredients';
import ApiToken from '../ApiToken';
import CustomLogging from '../../CustomLogging';


class BuildMeal {

  constructor() {
    CustomLogging.info("Building Meal...");
  }

  getMealPromiseObj(apiPath) {
    var mealList = [];
    var api;
    const apiToken = new ApiToken();

    var obj = {
      method: 'GET',
      headers: {
        'cache-control': "no-cache",
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + apiToken.getCookie('yuma-token')
      }
    }

    switch (apiPath) {


      case 'api/meals/all':

        api = 'api/meals/all';
        break;

      case 'api/meals/availablemeals':

        api = 'api/meals/availablemeals';

        break;

      default:

    }

    return fetch(api, obj)
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
          mealList.push(this.JSONobjToMeal(obj));
          
        }
        return mealList;
      });
  }

  addMeal(someMeal) {

    const apiToken = new ApiToken();
    const API = 'api/meals';

    CustomLogging.info('Adding: ' + someMeal.toString());

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

  // given a JSON meal object, return a Javascript Meal object.
  JSONobjToMeal(obj) {
    var ingredientsList = [];

    for (var j in obj.ingredients) {
      var ingredientObj = obj.ingredients[j];

      var ingredients = new Ingredients();
      ingredients.setName(ingredientObj.name);
      ingredients.setWeight(ingredientObj.weight);
      ingredients.setCalories(ingredientObj.calories);
      ingredients.setPrice(ingredientObj.price);
      ingredients.setAllergens(ingredientObj.allergens);  //TODO: implement proper loop add
      ingredients.setHealthLabels(ingredientObj.healthLabels);  //TODO: implement proper loop add

      ingredientsList.push(ingredients);
    }
    console.log(ingredientsList);
    var meal = new Meal();
    meal.setMealId(obj.mealId);
    meal.setName(obj.name);
    meal.setDescription(obj.description);
    meal.setIsAvailable(obj.available);
    meal.setFlags(obj.flags);
    meal.setIngredients(ingredientsList);
    meal.setHealthLabels(obj.healthLabels);
    meal.setProteinTypes(obj.proteinTypes);
  
    return meal;
  }

}

export default BuildMeal;
