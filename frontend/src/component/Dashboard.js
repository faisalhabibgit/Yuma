import React, {Component} from 'react';
import ApiToken from "../middleware/ApiToken";

import {
  Card, CardHeader,  CardBody,
  CardDeck, 
} from 'reactstrap';
import AvailableMeals from "./AvailableMeals";
import DisplayMeals from "./DisplayMeals";
import MealCombo from "./MealCombo";
import CustomLogging from '../CustomLogging';
import DisplayProteinScore from "./DisplayProteinScore";

class Dashboard extends Component {


  constructor(props) {
    super(props);
  
    this.checkAuthenticated();
  }

  componentDidMount() {
    window.scrollTo(0, 0);
  }

  

  checkAuthenticated(){

    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      CustomLogging.error('Check Authentification Dashboard: FAIL','dashboard');
      this.props.history.push(`/Login`)
    } else {
      CustomLogging.info('Check Authentification Dashboard: PASS','Dashboard');
    }

  }

  render() {
    return (
      <div style={{background: '#ADB7BF'}}>

        <CardDeck data-test="meal-combo-card" style={{padding:'12px', height:'480px'}}>
          <Card style={{padding:'12px', height:'450px'}} body outline color="primary">
            <CardHeader data-test="all-meals-card" className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black'}}> Weekly Meal Combination Report</h5>
            </CardHeader>
            <CardBody>
            <MealCombo/>  
            </CardBody>
          </Card>

          <Card style={{padding:'12px', height:'450px'}} body outline color="primary">
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black'}}> Available Meals</h5>
            </CardHeader>
            <CardBody>
              <AvailableMeals />
            </CardBody>
          </Card>

        </CardDeck>
        
        <CardDeck data-test="available-meal-card" style={{padding:'12px', height:'500px'}}>

          <Card style={{padding:'12px', height:'450px'}} body outline color="primary">
            <CardHeader data-test="all-meals-card" className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black'}}> All Meals</h5>
            </CardHeader>
            <CardBody>
              <DisplayMeals />
            </CardBody>
          </Card>

          <Card style={{padding:'12px', height:'450px'}} body outline color="primary">
            <CardHeader data-test="all-meals-card" className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black'}}> Protein Type Score</h5>
            </CardHeader>
            <CardBody>
              <DisplayProteinScore />
            </CardBody>
          </Card>

        </CardDeck>
        
      </div>
    );

  }
}


export default Dashboard;
