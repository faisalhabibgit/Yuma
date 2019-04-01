class MealCombo {

    constructor() {   
      this.numberOfBlanks = null;
      this.combinationScore = null;
      this.consumerList = null;
      this.mealsList = null;
    }

    setNumberOfBlanks(numberOfBlanks){this.numberOfBlanks = numberOfBlanks}
    setCombinationScore(combinationScore){this.combinationScore = combinationScore}
    setConsumerList(consumerList){this.consumerList = consumerList}
    setMealsList(mealsList){this.mealsList = mealsList}
  
    toString() {
        return 'this is a meal combo with ' + this.numberOfBlanks + ' blanks';
    }
}

export default MealCombo;
