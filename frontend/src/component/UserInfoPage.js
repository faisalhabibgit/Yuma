import React, { Component } from 'react';
import { Link } from "react-router-dom";
import {
  Col, Form,
  FormGroup, Label, Input,   ListGroup, ListGroupItem,
  Button, CardTitle, CardText, CardFooter,
  Container,Card, CardHeader,  CardBody,
  CardDeck
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import CustomLogging from '../CustomLogging';
import UserPlan from './UserPlan';

var hiddenToggle = {
  display: 'block'
}

export default class UserInfoPage extends Component{
  constructor(props) {
    super(props);

    this.checkAuthenticated();
    this.state = {
      isShowing: false
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
    if(this.props.consumerComments){
      return(
        <ListGroup>
          {this.props.consumerComments.map(consumerComments =>
            <ListGroupItem>{consumerComments}</ListGroupItem>
          )}
        </ListGroup>
      );
    }
  }

  displayDislikeList() {
    if(this.props.dislikesList){
      return(
        <ListGroup>
          {this.props.dislikesList.map(dislikes =>
            <ListGroupItem>{dislikes}</ListGroupItem>
          )}
        </ListGroup>
      );
    }
  }

  displayLikes() {
    if(this.props.likes){
      return(
        <ListGroup>
          {this.props.likes.map(like =>
            <ListGroupItem>{like}</ListGroupItem>
          )}
        </ListGroup>
      );
    }
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

  toggleHidden(event) {
    var checkboxValue = event.target.checked;
    this.setState({
      hiddenCheckbox: checkboxValue
    })
    if(this.hiddenCheckbox){
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
        Show consumer info <input type="checkbox" name="hiddenCheckbox" checked={this.state.hiddenCheckbox} onChange={this.toggleHidden}/>
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
