class Plan {

  constructor() {
    this.numOfMeals = null;
    this.details = null;
    this.specialRequests = null;
    this.requestedProteinTypes = null;
    this.diet = null;
  
  }

    setNumOfMeal(numOfMeals){ this.numOfMeals = numOfMeals }
    setDetails(details){ this.details = details}
    setSpecialRequests(specialRequests){this.specialRequests = specialRequests}
    setRequestedProteinTypes(requestedProteinTypes){this.requestedProteinTypes = requestedProteinTypes}
    setDiet(diet){this.diet = diet}



  toString() {
    return (this.numOfMeals + ', ' + this.extraVeggies + ', ' + this.extraProtein + ', ' + this.details);
  }
}

export default Plan;
