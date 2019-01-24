class Ingredients { 

    constructor(name, weight, calories, price, optional) {
      this.name = name;
      this.weight = weight;
      this.calories = calories;
      this.price = price;
      this.optional = optional;
    }
  
    toString() {
      return (this.name + this.weight + this.calories + this.price + this.optional);
    }
}
export default Ingredients;
