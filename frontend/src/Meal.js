import React from 'react';
import ReactDom from 'react-dom';
// import { BrowserRouter as Router, Route, Link } from "react-router-dom";

export default class Meal extends React.Component{
  constructor(props){
    super(props);
    this.state={
      meal:{chef: '', price: '', foodGroup: {}, description: '', availability: ''},
      mealArray: [],
      meals: [],
    }
  }


    // componentDidMount() {
    //   fetch('api/meals')
    //   .then(response => response.json())
    //   .then(data => this.setState({ meals: data[0].meals}));
    // }

  //lifecycle method fetch + api call
    componentDidMount() {
//fetch(<Router><Link className="apiCall" to="/meals"></Link></Router>).then(results => {
      fetch('api/meals')
        .then(results => {
          if(results.ok){
            console.log("results is the following");
            console.log(results);
            return results.json();
          }else {
            throw new Error('Something went Wrong with api/meals');
          }

        return results;
        // return results.json();    //result is a json
      }).then(data => {
        console.log(data.results);
        let mealArray = data.results.map((ingredients) => {    //map over the data
          // console.log("logging stuff !!!!!")
          // console.log(meals);

          return(
            <div key={ingredients.mealId}>        {/*setting the key*/}
              <ul>                          {/*setting display*/}
                <li>{ingredients.mealId}</li>
                <li>{ingredients.description}</li>
                <li>{ingredients.ingredients}</li>
                <li>{ingredients.available}</li>
              </ul>
            </div>
          )
        })
        this.setState({mealArray: mealArray});  //set the state with this.setState
      })
    }

    render() {
      return (
        <div className="Container">
          <div className="Meals">
            {this.state.mealArray}
          </div>
        </div>
      )
    }
  // the following code will change once we have api calls that gets meal from specific chef
  // showModifyForm(){
  //   let meal = {}
  // }



  render() {
    const { meals } = this.state;

    return (
      <ul>
        {meals.map(ingredients =>
          <li key={ingredients.ingredients.mealId}>
            <li>{ingredients.ingredients.description}</li>
            <li>{ingredients.ingredients.ingredients}</li>
            <li>{ingredients.ingredients.available}</li>
          </li>
        )}
      </ul>
    );
  }
}
