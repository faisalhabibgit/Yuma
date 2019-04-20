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

    const apiToken = new ApiToken();

    var comboSearchAPI = 'api/combinationreport/search?startdate='+
    this.state.startDate+'&enddate='+
    this.state.endDate;

    var obj = {
      method: 'GET',
      headers: {
          'cache-control': "no-cache",
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + apiToken.getCookie('yuma-token')
          //'Bearer ' + apiToken.getCookie('yuma-token')
      }
    }

    fetch(comboSearchAPI,obj)
    .then(response => response.json())
    .then(data => console.log(data))

    
    

  }

  componentDidMount() {
    window.scrollTo(0, 0);
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
      
                <Button onClick={this.getCombo}>View</Button>
          </Form>

      </Col>

    </Container>
  );
  }
}
export default MealHistory; 

