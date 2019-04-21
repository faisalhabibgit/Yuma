import React, { Component } from 'react';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import ApiToken from "../middleware/ApiToken";
import {  
  Container, Col, Form,
  FormGroup, Label, 
  Button,
  Card, CardDeck, CardBody, CardHeader} from 'reactstrap';
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
      endDate: new Date(),
      error: '',
      array: []
    };

    this.handleStart = this.handleStart.bind(this);
    this.handleEnd = this.handleEnd.bind(this);
    this.getCombo = this.getCombo.bind(this);
    
  }

  getCombo(){

    var startFormatted = this.convertToDateOnly(this.state.startDate);
    var endFormatted = this.convertToDateOnly(this.state.endDate);

    console.log('formatted date '+startFormatted)
    const apiToken = new ApiToken();
    var comboSearchAPI = 'api/combinationreport/search?startdate='+startFormatted
    +'&enddate='+endFormatted;

    var obj = {
      method: 'GET',
      headers: {
          'cache-control': "no-cache",
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + apiToken.getCookie('yuma-token')
      }
    }

    fetch(comboSearchAPI,obj)
    .then(response => response.json())
    .then(data => this.setState({array: data},console.log(data)))

  }

  componentDidMount() {
    window.scrollTo(0, 0);
  }
  
  convertToDateOnly(date){

    
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var dt = date.getDate();

    if (dt < 10) {
      dt = '0' + dt;
    }
    if (month < 10) {
      month = '0' + month;
    }

    return year+'-' + month + '-'+dt;

  }
  handleStart(date){

    this.setState({
      startDate: date
    });
    
  }

  handleEnd(date){

    this.setState({
      endDate: date
    });
  }


  render() {

    const items = this.state.array.map(element => 
     <div className="text-center">
        <br /> 
        <h1> Result </h1>   
        <li> Combination Score: {element.combinationScore} </li>
        <li> Number of Blanks: {element.numberOfBlanks} </li>
        <li> Created on: {element.createdOn} </li>
        <li> Users Combinations</li>
            
        {element.consumerTOS.map(user => 
        <ul>
          <li>{user.firstName}</li>

        </ul>

        )}
            
        <br />
      </div>
    );

    return (
    <Container>

      <Col sm="12" md={{ size: 12}}>
          <CardHeader  className="text-center" style={{background: '#B9C5D5', borderRadius: 10}}>
          <h2>Meal Combination History</h2>
          </CardHeader>
          <br />
          <Form className="form" onSubmit={this.handleSubmit}>
            <Col >
              <FormGroup>
                  <CardDeck data-test="name" style={{padding:'12px', height:'200px', borderRadius: 10}}>
                    

                    <Card  data-test="start-date" style= {{background:'#D0DCE5', borderRadius: 10, borderColor:'#274F6C'}}>                  
                    <CardBody className="text-center" style={{padding:'50px'}}>
                    <Label style={{color: 'black', fontSize:20}} >Start Date</Label>
                    <br />
                      <DatePicker 
                          selected={this.state.startDate} onChange={this.handleStart} mode="date"
                           />
                    </CardBody>
                    </Card>      
                  
    
                    <Card  data-test="end-date" style= {{background:'#D0DCE5', borderRadius: 10, borderColor:'#274F6C'}}>                  
                    <CardBody className="text-center" style={{padding:'50px'}}>
                    <Label style={{color: 'black', fontSize:20}}>End Date</Label>
                    <br />
                      <DatePicker selected={this.state.endDate} onChange={this.handleEnd} />
                    </CardBody>
                    </Card> 
                  </CardDeck>
             </FormGroup>
             
           </Col>
      
                
          </Form>

      </Col>
      <div className="text-center">
        <Button style={{width: 105, height: 50}}onClick={this.getCombo}>View</Button>

        <ul>
        {items}
        </ul>
      </div>
      
      
    </Container>
  );
  }
}
export default MealHistory; 

