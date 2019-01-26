import React, { Component } from 'react';
import Retriever from '../middleware/Retriever';

import ApiToken from "../middleware/ApiToken";



class AvailableMeals extends Component {


  constructor(props) {
    super(props);

    const apiToken = new ApiToken();
    if(!apiToken.isAuthenticated()){
      console.log('User Not Logged');
      this.props.history.push(`/Login`)
    }else{
      console.log('User Login Success');
    }

    this.state = {
    
      Meals: {}
    }

    
  }

  componentDidMount() {
    const retriever = new Retriever('api/meals/availablemeals');
    retriever.getEntityPromise()
      .then((obj) => {
        this.setState({ Meals: obj });
      })
  }
 
  doSearch() {
    var matchedArr = [];

    for (var i = 0; i < this.state.Meals.length; i++) {
      {
        matchedArr.push(this.state.Meals[i].description.toString());
      }
    }
    return matchedArr
  }
 
  render () {
    
    var Meals = this.doSearch()
   
    return (
      
      <div> {Meals} </div>
    );
  }
}
  


export default AvailableMeals;
