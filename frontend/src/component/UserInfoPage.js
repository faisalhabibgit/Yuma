import React, { Component } from 'react';
import { Link } from "react-router-dom";
import {
  Col, Form,
  FormGroup, Label, Input,
  Button, ListGroup, ListGroupItem,
  Container,Card, CardHeader,  CardBody, CardTitle, CardText, CardFooter,
  CardDeck
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import CustomLogging from '../CustomLogging';
import UserPlan from "./UserPlan";

var hiddenToggle = {
  display: 'block'
}

export default class UserInfoPage extends Component{
  constructor(props) {
    super(props);

    this.checkAuthenticated();
    this.state = {
      // firstName: "",
      // lastName: "",
      // plan: {
      //   numOfMeals: "",
      //   specialRequests: [],
      //   requestedProteinTypes: [],
      //   diet: "NONE",
      // },
      // isActive: "",
      // company: "",
      // allergies: [],
      // consumerComments: [],
      // dislikesList: [],
      // likes: [],
    }

    this.displayPlan = this.displayPlan.bind(this);
    this.displayAllergies = this.displayAllergies.bind(this);
    this.displayConsumerComments = this.displayConsumerComments.bind(this);
    this.displayDislikeList = this.displayDislikeList.bind(this);
    this.displayLikes = this.displayLikes.bind(this);
    this.toggleHidden = this.toggleHidden.bind(this);
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

  displayAllergies() {
    return(
      <ListGroup>
        {this.props.allergies.map(allergy =>
          <ListGroupItem>{allergy}</ListGroupItem>
        )}
      </ListGroup>
    );
  }

  displayConsumerComments() {
    return(
      <ListGroup>
        {this.props.consumerComments.map(consumerComments =>
          <ListGroupItem>{consumerComments}</ListGroupItem>
        )}
      </ListGroup>
    );
  }

  displayDislikeList() {
    return(
      <ListGroup>
        {this.props.dislikesList.map(dislikes =>
          <ListGroupItem>{dislikes}</ListGroupItem>
        )}
      </ListGroup>
    );
  }

  displayLikes() {
    return(
      <ListGroup>
        {this.props.likes.map(like =>
          <ListGroupItem>{like}</ListGroupItem>
        )}
      </ListGroup>
    );
  }

  displayPlan() {
    return(
      <div>
      <UserPlan
        numOfMeals = {this.props.plan.numOfMeals}
        specialRequests = {this.props.plan.specialRequests}
        requestedProteinTypes = {this.props.plan.requestedProteinTypes}
        diet = {this.props.plan.diet}>
      </UserPlan>
      </div>
    )
  }

  toggleHidden() {
    var checkbox = document.getElementById("hiddenCheckbox")
    if(checkbox.checked == true){
      hiddenToggle = {
        display: "none"
      }
    } else {
      hiddenToggle = {
        display: "block"
      }
    }
  }


  render(){
    return(
      <div>
        Show consumer info <input type="checkbox" id="hiddenCheckbox" onClick={this.toggleHidden()}/>
        <Card style={hiddenToggle}>
          <CardHeader>Plan</CardHeader>
          <CardBody>
            <CardTitle>{this.props.lastName}, {this.props.firstName}</CardTitle>
            <CardText>
              {this.displayPlan()}
              <ListGroup>
                <ListGroupItem>isActive: {this.props.isActive}</ListGroupItem>
                <ListGroupItem>Company: {this.props.company}</ListGroupItem>
                <h5>Allergies: </h5>
                  {this.displayAllergies()}
                <h5>Consumer Comments: </h5>
                  {this.displayConsumerComments()}
                <h5>Dislikes: </h5>
                  {this.displayDislikeList()}
                <h5>Likes: </h5>
                  {this.displayLikes()}
              </ListGroup>
            </CardText>
          </CardBody>
          <CardFooter> Diet: {this.props.diet} </CardFooter>
        </Card>
      </div>
    )
  }
}
