import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import yuma from '../component/images/yuma.png';
import history from './images/history.png';
import blog from './images/blog.png';
import dashboard from './images/dashboard.png';
import search from './images/search.png';
import add from './images/add.png';
import {
    CardDeck,
    Card,  CardText, CardBody,
    CardTitle
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import CustomLogging from '../CustomLogging';

class Home extends Component {
  constructor(props) {
    super(props);

    this.checkAuthenticated();
  }


  checkAuthenticated(){
    const apiToken = new ApiToken();
    if(!apiToken.isAuthenticated()){
      CustomLogging.error('User Not Logged','Home');
      this.props.history.push(`/Login`)
    }else{
      CustomLogging.info('User Login Success','Home');
    }
  }
    render() {
        return (
          <div>

            <div className="text-center">
              <img src={yuma}  width='100%' height='450px' alt="yuma"/>
            </div>

            <div className="text-center" style={{padding:'25px'}}>
              <h1 style={{color: '#599BE9'}}>Manage</h1>
              <h1>_________________________</h1>
            </div>

            <div className="text-center" >
              <CardDeck  style={{padding:'20px', height:'250px'}}>

                <Card  style={{border:'none', height:'120px'}}>
                  <CardBody data-test="stats-card">
                    <Link to="/Dashboard"> <img data-test='stats-button' src={dashboard}   height='120px' alt="dashboard"/> </Link>
                    <CardTitle>
                      <h3 style={{color: '#599BE9'}}>Dashboard</h3>
                    </CardTitle>
                    <CardText>Meal Combination Reports & Stats</CardText>
                  </CardBody>
                </Card>

                <Card  style={{border:'none', height:'200px'}}>
                  <CardBody data-test="meal-card">
                    <Link to="/SearchMeal"> <img data-test='meal-button' src={search}  height='120px' alt="search"/> </Link>
                    <CardTitle>
                      <h3 style={{color: '#599BE9'}}>Meals</h3>
                    </CardTitle>
                    <CardText>Search the Meal Inventory</CardText>
                  </CardBody>
                </Card>
                
                <Card  style={{border:'none', height:'200px'}}>
                  <CardBody data-test="add-meal-card">
                    <Link to="/NewMeal"> <img data-test='add-meal-button' src={add}  height='120px' alt="add"/> </Link>
                    <CardTitle>
                      <h3 style={{color: '#599BE9'}}>Add Meals</h3>
                    </CardTitle>
                    <CardText>Add a New Recipe  </CardText>
                  </CardBody>
                </Card>

                <Card  style={{border:'none', height:'200px'}}>
                  <CardBody data-test="meal-history-card">
                    <Link to="/MealHistory"> <img  data-test='history-button' src={history}  height='120px' alt="history"/> </Link>
                    <CardTitle>
                      <h3 style={{color: '#599BE9'}}>Meal History</h3>
                    </CardTitle>
                    <CardText>View Past Meal Combinations </CardText>
                  </CardBody>
                </Card>

                <Card  style={{border:'none', height:'200px'}}>
                  <CardBody data-test="blog-card">
                    <Link to="/Blog"> <img  data-test='blog-button' src={blog}  height='120px' alt="blog"/> </Link>
                    <CardTitle>
                      <h3 style={{color: '#599BE9'}}>Blog</h3>
                    </CardTitle>
                    <CardText>Access the Blog</CardText>
                  </CardBody>
                </Card>

              </CardDeck>

            </div>

          </div>
        );

    }

}


export default Home;
