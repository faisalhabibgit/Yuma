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

  //the following code will change once we have api calls that gets meal from specific chef
  // showModifyForm(){
  //   let meal = {}
  // }
}
