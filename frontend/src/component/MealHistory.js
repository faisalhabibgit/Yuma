import React, { Component } from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import ApiToken from "../middleware/ApiToken";
import {  Container, Col, Form,  FormGroup, Label, Input,  Button,} from 'reactstrap';
import CustomLogging from "../CustomLogging";


class MealHistory extends Component{

  constructor(props) {
    super(props);
    
    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      CustomLogging.error('Check Authentification MealHistory: FAIL','MealHistory');
      this.props.history.push(`/Login`)
    } else {
      CustomLogging.info('Check Authentification MealHistory: PASS','MealHistory');
    }
    
    this.state = {
      startDate: new Date(),
      error: '',
    };

    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    window.scrollTo(0, 0);
  }
  
  handleChange(date)
  {
    this.setState({
      startDate: date
    });
  }
  render() {
    return (
    <Container>
      <Col sm="12" md={{ size:20, offset: 15 }}>
      <h2>Meal Combination History</h2>
        <Form className="form-inline">

      <Col >
      <FormGroup>
      <Label>User Name</Label>
    <Input
    type="text"
    name="userName"
    id="userName"
    placeholder=""
    onChange={this.handleChange}
    />

    </FormGroup>
    </Col>
      <Col >
      <FormGroup>
      <Label>From: </Label>
    <DatePicker
    selected={this.state.startDate}
    onChange={this.handleChange}
    />
    </FormGroup>
    </Col>
    <Col >
    <FormGroup>
    <Label>To__: </Label>
    <DatePicker
    selected={this.state.startDate}
    onChange={this.handleChange}
    />
      
    </FormGroup>
    </Col>
      
    <Button>View</Button>
    </Form>
    </Col>
  
    </Container>
  );
  }
}
export default MealHistory; 

