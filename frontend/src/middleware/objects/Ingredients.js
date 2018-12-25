import React, { Component } from 'react';

class Ingredients extends Component { 

    constructor(name, weight, calories, price) {
      this.name = name;
      this.weight = weight;
      this.calories = calories;
      this.price = price;
    }
  
    toString() {
      console.log(this.name + this.weight + this.calories + this.price);
    }
}

export default Ingredients;