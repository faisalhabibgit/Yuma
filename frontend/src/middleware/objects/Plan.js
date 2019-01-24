class Plan {

  constructor(numOfMeal, extraVeggies, extraProtein, details) {
    this.numOfMeal = numOfMeal;
    this.extraVeggies = extraVeggies;
    this.extraProtein = extraProtein;
    this.details = details;
    
  }

  toString() {
    return (this.numOfMeal + this.extraVeggies + this.extraProtein + this.details);
  }
}

export default Plan;
