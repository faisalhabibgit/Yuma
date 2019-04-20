import React, { Component } from 'react';
import { Link } from "react-router-dom";
import {
  Col, Form,
  FormGroup, Label, Input,
  Button,
  Container,Card, CardHeader,  CardBody,
  CardDeck
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import CustomLogging from '../CustomLogging';

export default class UserInfoPage extends Component{
  constructor(props) {
    super(props);

    this.checkAuthenticated();
    this.state = {
      firstName: "",
      lastName: "",
      plan: {
        numOfMeals: "",
        specialRequests: [],
        requestedProteinTypes: [],
        diet: "NONE",
      },
      isActive: "",
      company: "",
      allergies: [],
      consumerComments: [],
      dislikesList: [],
      likes: [],
    }

    this.displayPlan = this.displayPlan.bind(this);
  }

  checkAuthenticated(){

    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      CustomLogging.error('Check Authentification UserInfoPage: FAIL','UserInfoPage');
      this.props.history.push(`/Login`)
    } else {
      CustomLogging.info('Check Authentification UserInfoPage: PASS','UserInfoPage');
    }
  }

  
  displayPlan() {
    return(
      <UserPlan
        numOfMeals = {this.props.plan.numOfMeals}
        specialRequests = {this.props.plan.specialRequests}
        requestedProteinTypes = {this.props.plan.requestedProteinTypes}
        diet = {this.props.plan.diet}>
      </UserPlan>
    )
  }


  render(){
    return(
      <div>
        <Card>
          <CardHeader>Plan</CardHeader>
          <CardBody>
            <CardTitle>{this.props.lastName}, {this.props.firstName}</CardTitle>
            <CardText>
              {this.displayPlan()}
              <ListGroup>
                <ListGroupItem>isActive: {this.props.isActive}</ListGroupItem>
                <ListGroupItem>Company: {this.props.company}</ListGroupItem>
                <h5>Allergies: </h5>
                  <ListGroup>{this.displayAllergies()}</ListGroup>
                <h5>Consumer Comments: </h5>
                  <ListGroup>{this.displayConsumerComments()}</ListGroup>
                <h5>Dislikes: </h5>
                  <ListGroup>{this.displayDislikeList()}</ListGroup>
                <h5>Likes: </h5>
                  <ListGroup>{this.displayLikes()}</ListGroup>
              </ListGroup>
            </CardText>
          </CardBody>
          <CardFooter> Diet: {this.props.diet} </CardFooter>
        </Card>
      </div>
    )
  }
}
