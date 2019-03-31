import React, {Component} from 'react';
import ApiToken from "../middleware/ApiToken";

import {
  Card, CardHeader,  CardBody,
  CardDeck, Button
} from 'reactstrap';
import AvailableMeals from "./AvailableMeals";
import DisplayMeals from "./DisplayMeals";
import DisplayUsers from "./DisplayUsers";
import MealCombo from "./MealCombo";

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
      console.log('Check Authentification Dashboard: FAIL');
      this.props.history.push(`/Login`)
    } else {
      console.log('Check Authentification Dashboard: PASS');
    }

  }

  render() {
    return (
      <div style={{background: '#ADB7BF'}}>

        <CardDeck data-test="meal-combo-card" style={{padding:'12px', height:'380px'}}>
          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h2 style={{color: 'black'}}> Meal Combination Reports</h2>
            </CardHeader>
            <CardBody>
            <MealCombo/>  
            </CardBody>
          </Card>

        </CardDeck>


        <CardDeck data-test="available-meal-card" style={{padding:'12px', height:'450px'}}>
          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black'}}> Available Meals</h5>
            </CardHeader>
            <CardBody>
              <AvailableMeals />
            </CardBody>
          </Card>

          <Card>
            <CardHeader data-test="all-meals-card" className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black'}}> All Meals</h5>
            </CardHeader>
            <CardBody>
              <DisplayMeals />
            </CardBody>
          </Card>

        </CardDeck>


        <CardDeck data-test="users-card" style={{padding:'12px', height:'450px'}}>
          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <div>
                <Button variant="secondary"  onClick={this.handleModalChange3}>Update User</Button>
              </div>
            </CardHeader>
            <CardBody>
              <DisplayUsers />
            </CardBody>
          </Card>

        </CardDeck>

        
      </div>
    );

  }
}


export default Dashboard;
