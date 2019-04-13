class Ingredients { 

    constructor() {
      this.name = null;
      this.weight = null;
      this.calories = null;
      this.price = null;
      this.optional = null;
      this.allergens = null;
      this.healthLabels = null;
    }

    setName(name){this.name = name}
    setWeight(weight){this.weight = weight}
    setCalories(calories){this.calories = calories}
    setPrice(price){this.price = price}
    setOptional(optional){this.optional = optional}
    setAllergens(allergens){this.allergens = allergens}
    setHealthLabels(healthLabels){this.healthLabels = healthLabels}
  
    toString() {
      return (this.name + this.weight + this.calories + this.price + this.optional);
    }
}
export default Ingredients;
