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
      <ul class="list-group">
      <li class="card-header"> The Meal ID !!! { this.props.mealId } </li>
      <li class="list-group-item"> Ingredients {this.displayIngredients()} </li>
      <li class="list-group-item"> Description { this.props.description } </li>
      </ul>
      </li>
    )
  }
}
