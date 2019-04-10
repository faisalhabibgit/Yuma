import React, {Component} from 'react';
import ApiToken from "../middleware/ApiToken";

import {
  Card, CardHeader,  CardBody,
  CardDeck
} from 'reactstrap';
import AvailableMeals from "./AvailableMeals";
import DisplayMeals from "./DisplayMeals";
import DisplayUsers from "./DisplayUsers";
import MealCombo from "./MealCombo";
import CustomLogging from '../CustomLogging';

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

        <CardDeck data-test="meal-combo-card" style={{padding:'12px', height:'380px'}}>
          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black', fontFamily: 'Comic Sans MS'}}> Meal Combo</h5>
            </CardHeader>
            <CardBody>
            <MealCombo/>  
            </CardBody>
          </Card>

        </CardDeck>


        <CardDeck data-test="available-meal-card" style={{padding:'12px', height:'450px'}}>
          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black', fontFamily: 'Comic Sans MS'}}> Available Meals</h5>
            </CardHeader>
            <CardBody>
              <AvailableMeals />
            </CardBody>
          </Card>

          <Card>
            <CardHeader data-test="all-meals-card" className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black', fontFamily: 'Comic Sans MS'}}> All Meals</h5>
            </CardHeader>
            <CardBody>
              <DisplayMeals />
            </CardBody>
          </Card>

        </CardDeck>


        <CardDeck data-test="users-card" style={{padding:'12px', height:'620px'}}>
          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black', fontFamily: 'Comic Sans MS'}}> Users </h5>
            </CardHeader>
            <CardBody>
              <DisplayUsers />
            </CardBody>
          </Card>

        </CardDeck>


        <CardDeck style={{padding:'12px', height:'275px'}}>
          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black', fontFamily: 'Comic Sans MS'}}> Header</h5>
            </CardHeader>
            <CardBody>
            </CardBody>
          </Card>


          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black', fontFamily: 'Comic Sans MS'}}>Header</h5>
            </CardHeader>
            <CardBody>
            </CardBody>
          </Card>

        </CardDeck>

      </div>
    );

  }
}


export default Dashboard;
