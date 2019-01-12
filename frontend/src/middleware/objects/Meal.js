class Meal {

    constructor(mealId, description, isAvailable, flags, ingredients) {      
      this.mealId = mealId;
      this.description = description;
      this.isAvailable = isAvailable;
      this.flags = flags;
      }
  
    toString() {
      return this.mealId + this.description + this.isAvailable + this.flags;
    }
}

export default Meal;
