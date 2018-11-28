import React, { Component } from 'react';

export default class MealComponent extends Component{


  displayIngredients(){
    let ingredients = []

    for(let i in this.props.ingredients){
      ingredients.push(
        <li key={i}>
        {i}{this.props.ingredients[i].name}
        </li>
      )
    }
    return(<ul>{ingredients}</ul>);
  }

  render(){
    return(
      <li key={this.props.mealId}>
      <ul>
      <li> The Meal ID !!! { this.props.mealId } </li>
      <li> Ingredients {this.displayIngredients()} </li>
      <li> Description { this.props.description } </li>
      </ul>
      </li>
    )
  }
}
