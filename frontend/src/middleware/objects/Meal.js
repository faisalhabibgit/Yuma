class Meal {

    constructor(mealId, name, description, isAvailable, flags, ingredients) {   
      console.log('inside meal constructor'); 
      this.mealId = mealId;
      this.name = name;
      this.description = description;
      this.isAvailable = isAvailable;
      this.flags = flags;
      this.ingredients = ingredients;
      }
  
    toString() {
      console.log('inside meal toString');   
      var ingredientsList = [];
      
      for (let index = 0; index < this.ingredients.length; index++) {
        const element = this.ingredients[index];
        ingredientsList.push(element.toString() + '\n');
      }

      return (this.name +', '+ this.description +', '+ this.isAvailable +', '+ this.flags + ', ' + ingredientsList.toString());
    }
}

export default Meal;
