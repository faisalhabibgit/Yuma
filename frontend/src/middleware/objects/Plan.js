class Plan {

  constructor() {
    this.numOfMeal = null;
    this.extraVeggies = null;
    this.extraProtein = null;
    this.details = null;
  
  }

    setNumOfMeal(numOfMeal){ this.numOfMeal = numOfMeal }
    setExtraVeggies(extraVeggies){ this.extraVeggies = extraVeggies }
    setExtraProtein(extraProtein){ this.extraProtein = extraProtein }
    setDetails(details){ this.details = details}



  toString() {
    return (this.numOfMeal + ', ' + this.extraVeggies + ', ' + this.extraProtein + ', ' + this.details);
  }
}

export default Plan;
