import React, { Component } from 'react';
import {
  ListGroup, ListGroupItem,
  Container, Col, Form, FormGroup, Label, Input
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import Retriever from "../middleware/Retriever";
import CustomLogging from "../CustomLogging";

const API = "api/rest/company/"

class UserByCompanyName extends Component {

  constructor(props) {
    super(props);

    this.checkAuthenticated();
    this.state = {
      userInput: "",
      // apiObject: [{company:"google", email: "email", firstname: "some first name", lastname: "some last name"}
      //             ],
      apiObject: null,
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
    const retriever = new Retriever(API + company);
    CustomLogging.info('retrieving company user list', 'UsersByCompanyName');
    retriever.getEntityPromise()
    // fetch(API + company)
    .then((obj) => {
      console.log(obj)

      this.setState({ apiObject: obj})
    })
  }

  pushMatchedArr(thingy){
    var matchedArr = [];

    matchedArr.push(" email: "  + thingy.email.toString()  +
                    "  First Name: " + thingy.firstname.toString() +
                    "  Last Name: " + thingy.lastname.toString());
  }

  pushMatchedArrWithNoEmail(thingy){
    var matchedArr = [];

    matchedArr.push(" email: No email registered"   +
                    "  First Name: " + thingy.firstname.toString() +
                    "  Last Name: " + thingy.lastname.toString());
  }


  display() {
    debugger

    var matchedArr = [];
    for (var i = 0; i < this.state.apiObject.length; i++) {
      console.log("this.state.apiObject[i].company: " + this.state.apiObject[i].company);
      console.log("this.state.userInput: " + this.state.userInput);
      console.log("this.state.apiObject[i].firstName" + this.state.apiObject[i].firstName);
      // validate but partially works
      // if (this.state.apiObject[i].company.toString().includes(this.state.userInput)
      // ) {

// !this.state.apiObject[i].email.length ? pushMatchedArr(this.state.apiObject): pushMatchedArrWithNoEmail(this.state.apiObject)

      // }
      matchedArr.push(" email: No email registered"   +
                      "  First Name: " + this.state.apiObject[i].firstname +
                      "  Last Name: " + this.state.apiObject[i].lastname);

    }
    console.log("matchedArr: " + matchedArr)
    this.setState({ searchList: matchedArr });
  }

  render() {
    return (
      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <Form className="form">
            <FormGroup>
              <input
                onChange = { (e)=>this.handleQueryChange(e.target.value)}
                value={this.state.userInput}
                type="text"
              />
            </FormGroup>
          </Form>
        </Col>
        <Col sm={{ size: 6, order: 2, offset: 1 }}>
          <ListGroup>
            {
              this.state.searchList.map(x => <ListGroupItem key={x}>{x}</ListGroupItem>)
            }
          </ListGroup>
        </Col>
      </Container>

    );
  }

}
export default UserByCompanyName;
