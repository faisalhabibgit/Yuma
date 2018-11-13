import React from 'react';
import ReactDom from 'react-dom';

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
      fetch('https://randomuser.me/api/?results=500').then(results => {
        return results.json();    //result is a json
      }).then(data => {
        let pictures = data.results.map((pic) => {    //map over the data
          return(
            <div key={meals.mealId}>        //setting the key
              <ul>                          //setting display
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
      return {
        <div>
          {this.state.mealArray}
        </div>
      }
    }
  //the following code will change once we have api calls that gets meal from specific chef
  // showModifyForm(){
  //   let meal = {}
  // }
}
