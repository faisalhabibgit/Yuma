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
import CustomLogging from '../CustomLogging';
import UsersByCompany from "./UsersByCompany";
import MealChart from "./MealChart";

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

        <CardDeck  style={{padding:'12px', height:'380px'}}>

          <Card data-test="meal-combo-card">
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h2 style={{color: 'black'}}> Meal Combination Reports</h2>
            </CardHeader>
            <CardBody>
              <MealCombo/>
            </CardBody>
          </Card>

        </CardDeck>

        <CardDeck style={{padding:'12px', height:'450px'}}>

          <Card data-test="available-meal-card">
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black'}}> Available Meals</h5>
            </CardHeader>
            <CardBody>
              <AvailableMeals />
            </CardBody>
          </Card>

          <Card data-test="all-meals-card">
            <CardHeader className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black'}}> All Meals</h5>
            </CardHeader>
            <CardBody>
              <DisplayMeals />
            </CardBody>
          </Card>

        </CardDeck>

        <CardDeck  style={{padding:'12px', height:'370px'}}>
          <Card data-test="users-per-company">
            <CardBody>
              <UsersByCompany />
            </CardBody>
          </Card>

          <Card data-test="meals-chart-card">
            <CardBody>
              <MealChart />
            </CardBody>
          </Card>
        </CardDeck>

        <CardDeck  style={{padding:'12px', height:'620px'}}>
          <Card data-test="users-card">
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
