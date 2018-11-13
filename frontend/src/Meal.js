import React from 'react';
import ReactDom from 'react-dom';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

export default class Meal extends React.Component{
  constructor(props){
    super(props);
    this.state={
      meal:{chef: '', price: '', foodGroup: {}, description: '', availability: ''},
      mealArray: [],
    }
  }
  //lifecycle method fetch + api call
    componentDidMount() {
      fetch('http://localhost:9000/meals').then(results => {
        console.log("results is the following");
        console.log(results)
        return results.json();    //result is a json
      }).then(data => {
        let mealArray = data.results.map((meals) => {    //map over the data
          // console.log("logging stuff !!!!!")
          // console.log(meals);
          return(
            <div key={meals.mealId}>        {/*setting the key*/}
              <ul>                          {/*setting display*/}
                <li>{meals.mealId}</li>
                <li>{meals.description}</li>
                <li>{meals.ingredients}</li>
                <li>{meals.available}</li>
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
  //the following code will change once we have api calls that gets meal from specific chef
  // showModifyForm(){
  //   let meal = {}
  // }
}
