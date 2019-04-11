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
                    <CardBody>
                    <Label >From</Label>
                    <br />
                      <DatePicker selected={this.state.startDate} onChange={this.handleChange} />
                    </CardBody>
                    </Card>      
                  
    
                    <Card  data-test="end-date" style= {{background:'#D0DCE5', borderRadius: 10, borderColor:'#274F6C'}}>                  
                    <CardBody>
                    <Label>To</Label>
                    <br />
                      <DatePicker selected={this.state.startDate} onChange={this.handleChange} />
                    </CardBody>
                    </Card> 
                  </CardDeck>
             </FormGroup>
           </Col>
      
                <Button type="view" value="view" size="lg" block>View</Button>
          </Form>

      </Col>
    </Container>
  );
  }
}
export default MealHistory; 

