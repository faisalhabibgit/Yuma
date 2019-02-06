class Plan {

  constructor() {
    this.numOfMeals = null;
    this.extraVeggies = null;
    this.extraProtein = null;
    this.details = null;
  
  }

    setNumOfMeal(numOfMeals){ this.numOfMeals = numOfMeals }
    setExtraVeggies(extraVeggies){ this.extraVeggies = extraVeggies }
    setExtraProtein(extraProtein){ this.extraProtein = extraProtein }
    setDetails(details){ this.details = details}



  toString() {
    return (this.numOfMeals + ', ' + this.extraVeggies + ', ' + this.extraProtein + ', ' + this.details);
  }
}

export default Plan;
