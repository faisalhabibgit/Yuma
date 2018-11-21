import React, { Component } from 'react';
const API = 'api/meals';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      mealsArray: [],
    };
  }

  componentDidMount() {
    console.log(fetch(API));
    fetch(API)
      .then(response => response.json())
      .then(response => console.log(response))
      .then(response => this.setState({ mealsArray: response })
      .then(console.log(this.state.mealsArray))
);
  }
  render() {

    const { mealsArray } = this.state;

    return (
      <ul>
        {mealsArray.map(meals =>
          <li key={meals.ingredients.mealId}>
          <ul>
          <li> { meals.ingredients.mealId } </li>
          <li> { meals.ingredients.description } </li>
          <li> { meals.ingredients.ingredients } </li>
          <li> { meals.ingredients.available } </li>
          </ul>
          </li>
        )}
      </ul>
    );
  }
}

export default App;
