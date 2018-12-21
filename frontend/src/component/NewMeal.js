import React, { Component } from 'react';
import {
  Col, Form,
  FormGroup, Label, Input,
  Button,
  Container,
} from 'reactstrap';


class NewMeal extends Component{
  render(){
    return(
        <Container>
          <Col sm="12" md={{ size: 6, offset: 3}}>
            <h2>Enter a New Meal</h2>
            
            <br/>
            
            <Form className="form">
              <Col >
                <FormGroup>
                  <Label>Meal Description</Label>
                  <Input
                    type="text"
                    name="description"
                    id="description"
                    placeholder="A tasty meal with X sauce."
                  />
                  
                  <br/>
                  
                  <Label>Ingredients</Label>
                  <Input
                    type="password"
                    name="password"
                    id="examplePassword"
                    placeholder="********"
                  />
                  
                  <br/>
                  
                  <Label>Allergies</Label>
                  
                </FormGroup>
              </Col>
              <Button>Submit</Button>
            </Form>
          </Col>
        
      </Container>
    );
  }
}

export default NewMeal;
