import React, { Component } from 'react';
import MealComponent from './MealComponent';
const API = 'api/meals';

class Meal extends Component {
  constructor(props) {
    super(props);

    this.state = {
      mealsArray: [],
    };
    this.setMeals = this.setMeals.bind(this);
  }

componentDidMount(){
  this.fetchData();
}

fetchData(){
  fetch(API)
  .then(response => response.json())
  .then(this.setMeals);   //response gets passed into setMeals because javascript magic
}

setMeals(data){

  this.setState({mealsArray:data})
}

displayMeals(){
  //this.state.mealsArray.map(meal=>console.log(meal));
  return(
  <ul>
    {  this.state.mealsArray.map(meals =>
      <MealComponent mealId={meals.mealId} description={meals.description} ingredients={meals.ingredients}/>
    )}
  </ul>
  );
}

  render() {

    //const {mealsArray}  = this.state;
    console.log(this.state.mealsArray);
    return (
      <div>
      {this.displayMeals()}
      </div>
    );
  }
}

export default Meal;
