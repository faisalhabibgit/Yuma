import React, { Component } from 'react';
import {
  ListGroup, ListGroupItem,
  Button, CardTitle, CardText,
  Card, CardHeader,  CardBody, Collapse
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import CustomLogging from '../CustomLogging';
import UserPlan from './UserPlan';

export default class UserInfoPage extends Component{
  constructor(props) {
    super(props);

    this.checkAuthenticated();
    this.state = {
      collapse: false,
      status: 'Closed'
    }

    this.displayPlan = this.displayPlan.bind(this);
    this.displayAllergies = this.displayAllergies.bind(this);
    this.displayConsumerComments = this.displayConsumerComments.bind(this);
    this.displayDislikeList = this.displayDislikeList.bind(this);
    this.displayLikes = this.displayLikes.bind(this);
    this.onEntering = this.onEntering.bind(this);
    this.onEntered = this.onEntered.bind(this);
    this.onExiting = this.onExiting.bind(this);
    this.onExited = this.onExited.bind(this);
    this.toggle = this.toggle.bind(this);
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


  onEntering() {
    this.setState({ status: 'Opening...' });
  }

  onEntered() {
    this.setState({ status: 'Opened' });
  }

  onExiting() {
    this.setState({ status: 'Closing...' });
  }

  onExited() {
    this.setState({ status: 'Closed' });
  }

  toggle() {
    this.setState(state => ({ collapse: !state.collapse }));
  }

  render(){
    return(
      <div>
          <Card>
            <CardHeader>
              <Button color="primary" onClick={this.toggle} style={{ marginBottom: '1rem' }}>Show consumer: {this.props.lastName}, {this.props.firstName}'s info
              </Button>
            </CardHeader>
            <Collapse
              isOpen={this.state.collapse}
              onEntering={this.onEntering}
              onEntered={this.onEntered}
              onExiting={this.onExiting}
              onExited={this.onExited}
            >
            <CardBody>
              <CardTitle>User Info</CardTitle>
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
                  <ListGroupItem> Diet: {this.props.diet} </ListGroupItem>
                </ListGroup>
              </CardText>
            </CardBody>
            </Collapse>
          </Card>
      </div>
    )
  }
}
