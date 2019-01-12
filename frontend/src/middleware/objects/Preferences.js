class Preferences {

  constructor(numOfMeal, extraVeggies, extraProtein, details) {
    this.mealId = numOfMeal;
    this.description = extraVeggies;
    this.isAvailable = extraProtein;
    this.flags = details;
    
  }

  toString() {
    return this.numOfMeal + this.extraVeggies + this.extraVeggies + this.details;
  }
}

export default Preferences;
