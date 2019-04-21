import React, { Component } from 'react';
import {
  ListGroup, ListGroupItem,
  Container, Col, Form, FormGroup, Label, Input
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import Retriever from "../middleware/Retriever";
import CustomLogging from "../CustomLogging";
import UserInfoPage from "./UserInfoPage"

const API = "api/rest/company/"

class UserByCompanyName extends Component {

  constructor(props) {
    super(props);

    this.checkAuthenticated();
    this.state = {
      userInput: "",
      // apiObject: [{company:"google", email: "email", firstname: "some first name", lastname: "some last name"}
      //             ],
      apiObject: [],
      searchList: []
    }

    this.handleQueryChange = this.handleQueryChange.bind(this);
    this.companyFetch = this.companyFetch.bind(this);
    this.display = this.display.bind(this);
  }

  checkAuthenticated(){
    const apiToken = new ApiToken();
    if(!apiToken.isAuthenticated()){
      console.log('User Not Logged');
      this.props.history.push(`/Login`)
    }else{
      console.log('User Login Success');
    }
  }

  handleQueryChange(input){

    this.setState({
      userInput: input
    }, this.companyFetch(input));
    if( (this.state.apiObject === null) || (this.state.apiObject === [])) {
    // if( this.state.apiObject === []) {
      this.setState({
        searchList: ["no result"]
      })
    }
    else{
      this.display()
      console.log("the user inputs: " + this.state.userInput)
    }
  }

  companyFetch(company){

    console.log(company)
    if(company){
      const retriever = new Retriever(API + company);
      CustomLogging.info('retrieving company user list', 'UsersByCompanyName');
      retriever.getEntityPromise()
      // fetch(API + company)
      .then((obj) => {
        console.log(obj)

        this.setState({ apiObject: obj})
      })
    }

  }

//Display Basic consumer information, and have a button available to view
// consumer details at the UserInfoPage

// Use a for loop for the basic information and passing the fetched information
// per user to UserInfoPage Component

// if possible open on a modal so its easily closed

  display() {

    return(
        <table >
            { this.state.apiObject.map(consumer =>
              <tr>
                <UserInfoPage
                  firstName = {consumer.firstName}
                  lastName = {consumer.lastName}
                  plan = {consumer.plan}
                  isActive = {consumer.isActive}
                  company = {consumer.company}
                  allergies = {consumer.allergies}
                  consumerComments = {consumer.consumerComments}
                  dislikesList = {consumer.dislikesList}
                  likes = {consumer.likes}>
                </UserInfoPage>
              </tr>
            )}
        </table>
    )
  }

  render() {
    return (
      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <Form className="form">
            <FormGroup>
            <Label for="searchCompany">Search Company Name</Label>
              <Input
                id="searchCompany"
                placeholder="Company Name"
                onChange = { (e)=>this.handleQueryChange(e.target.value)}
                value={this.state.userInput}
                type="text"
              />
            </FormGroup>
          </Form>
        </Col>
        <Col sm={{ size: 12, order: 2, offset: 1 }}>
          <ListGroup>
            {this.display()}
          </ListGroup>
        </Col>
      </Container>
    );
  }
}
export default UserByCompanyName;
