class Ingredients { 

    constructor(name, weight, calories, price) {
      this.name = name;
      this.weight = weight;
      this.calories = calories;
      this.price = price;
    }
  
    toString() {
      return this.name + this.weight + this.calories + this.price;
    }
}

export default Ingredients;