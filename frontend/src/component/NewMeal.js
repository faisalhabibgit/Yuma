import React, { Component } from 'react';
import {
  Col, Form,
  FormGroup, Label, Input,
  Button,
  Container,
} from 'reactstrap';


class NewMeal extends Component{

  constructor(props) {
    super(props);
    this.state = {
      name: '',
      description: '',
      ingredients: '',
      flags: [],
      nuts: ''
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit(event) {
    alert('the name is ' + this.state.name +'\nthe description is: '+this.state.description+'\n nuts are: '+this.state.nuts);
    event.preventDefault();
  }
  
  handleChange(event){
    
    const target = event.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const id = target.id;
    
    this.setState({[id]:value});
    
  }
  
  render(){
    return(
        <Container>
          <Col sm="12" md={{ size: 6, offset: 3}}>
            <h2>Enter a New Meal</h2>
            
            <br/>
            
            <Form className="form" onSubmit={this.handleSubmit}>
              <Col >
                <FormGroup>
                  <Label>Name</Label>
                  <Input
                    type="text"
                    name="name"
                    id="name"
                    placeholder="Chicken Parmesan"
                    onChange={this.handleChange}
                  />

                  <br/>
                  
                  <Label>Meal Description</Label>
                  <Input
                    type="text"
                    name="description"
                    id="description"
                    placeholder="Chicken basted in tomato sauce."
                    onChange={this.handleChange}
                  />
                  
                  <br/>
                  
                  <Label>Ingredients</Label>
                  <Input
                    type="text"
                    name="Ingredients"
                    id="Ingredients"
                    placeholder="tomatoes, chicken, garlic"
                    onChange={this.handleChange}
                  />
                  
                  <br/>

                  <Label style={{fontWeight: "bold"}}> Possible Food Allergies </Label>
                  
                  <FormGroup row>
                    <Col sm={{ size: 10 }}>
                      <FormGroup check>
                        <Label>
                          <Input type="checkbox" id="nuts" onChange={this.handleChange}/>
                          Tree Nuts
                        </Label>
                        <br/>
                        <Label>
                          <Input type="checkbox" id="dairy" onChange={this.handleChange}/>
                          Dairy
                        </Label>
                        <br/>
                        <Label>
                          <Input type="checkbox" id="gluten"onChange={this.handleChange}/>
                          Gluten
                        </Label>
                        <br/>
                        <Label>
                          <Input type="checkbox" id="shellfish" onChange={this.handleChange}/>
                          Shellfish
                        </Label>
                        <br/>
                        <Label>
                          <Input type="checkbox" id="soy" onChange={this.handleChange}/>
                          Soy
                        </Label>
                      </FormGroup>
                    </Col>
                  </FormGroup>
                  
                </FormGroup>
              </Col>
              <Button type="submit" value="Submit">Submit</Button>
            </Form>
          </Col>
        
      </Container>
    );
  }
}

export default NewMeal;
