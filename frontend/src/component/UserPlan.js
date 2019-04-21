import React, { Component } from 'react';
import { Link } from "react-router-dom";
import {
  Col, Form, ListGroup, ListGroupItem,
  FormGroup, Card, CardHeader, CardFooter, CardBody,
  CardTitle, CardText
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import CustomLogging from '../CustomLogging';

export default class UserPlan extends Component{
  constructor(props) {
    super(props);

    this.checkAuthenticated();
  }


  checkAuthenticated(){

    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      CustomLogging.error('Check Authentification UserPlan: FAIL','UserPlan');
      this.props.history.push(`/Login`)
    } else {
      CustomLogging.info('Check Authentification UserPlan: PASS','UserPlan');
    }
  }

  displaySpecialRequests() {
    return(
      <ListGroup>
        {this.props.specialRequests.map(request =>
          <ListGroupItem>{request}</ListGroupItem>
        )}
      </ListGroup>
    );
  }
  displayProteinTypes() {
    return(
      <ListGroup>
        {this.props.requestedProteinTypes.map(requestType =>
          <ListGroupItem>{requestType}</ListGroupItem>
        )}
      </ListGroup>
    );
  }

  render(){
    return(
      <div>
        <Card>
          <CardHeader>Plan</CardHeader>
          <CardBody>
            <CardTitle>Number of Meals: {this.props.numOfMeals}</CardTitle>
            <CardText>
              <h5>Special Requests: </h5>
                {this.displaySpecialRequests()}
              <h5>Requested Protein Types: </h5>
                {this.displayProteinTypes()}
            </CardText>
          </CardBody>
          <CardFooter> Diet: {this.props.diet} </CardFooter>
        </Card>
      </div>
    );
  }
}
