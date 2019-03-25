class Meal {

    constructor() {   
      this.mealId = null;
      this.name = null;
      this.description = null;
      this.isAvailable = null;
      this.flags = null;
      this.ingredients = null;
    }

    setMealId(mealId){this.mealId = mealId}
    setName(name){this.name = name}
    setDescription(description){this.description = description}
    setIsAvailable(value){this.isAvailable = value}
    setFlags(flags){this.flags = flags}
    setIngredients(ingredients){this.ingredients = ingredients}
  
    toString() {
      var ingredientsList = [];
      
      for (let index = 0; index < this.ingredients.length; index++) {
        const element = this.ingredients[index];
        ingredientsList.push(element.toString() + '\n');
      }

      return (this.name +', '+ this.description +', '+ this.isAvailable +', '+ this.flags + ', ' + ingredientsList.toString());
    }
}

export default Meal;
