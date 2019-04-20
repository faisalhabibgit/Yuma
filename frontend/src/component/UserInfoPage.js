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
      
    }
  }

  checkAuthenticated(){

    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      CustomLogging.error('Check Authentification NewMeal: FAIL','NewMeal');
      this.props.history.push(`/Login`)
    } else {
      CustomLogging.info('Check Authentification NewMeal: PASS','NewMeal');
    }
  }


  render(){
    return()
  }
}
