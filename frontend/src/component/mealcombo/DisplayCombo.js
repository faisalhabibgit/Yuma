import React, { Component } from 'react';
import {
  Container, Card,CardBody, CardGroup
} from 'reactstrap';

class DisplayCombo extends Component {
    constructor(props) {
        super(props);

        this.state = {
            apiObject: this.props.data,
            displayLimit: 10,
        }
    }

    render() {
        return (
          <Container className="text-center">
            <h1>Users</h1>
           
            { this.state.apiObject.consumerList.map(x => 
              
            <Card style={{padding:'20px',background:'#d3d3d3'}}>
              <CardBody>
                
                <div style={{padding:'20px'}}> <h4 style={{color:'#599BE9'}}> User Info </h4>
                <h5> First name: { x.firstName }</h5>
                <h5> Last Name: { x.lastName}</h5>
                <h5> Email: { x.email}</h5>
                </div>

                <div style={{padding:'20px'}}>
                  <h4 style={{color:'#599BE9'}}>User Details </h4>
                  <h5> Number of Meals: { x.plan.numOfMeal}</h5>
                  <h5> Extra Veggies: { x.plan.extraVeggies}</h5>
                  <h5> Extra Protein: { x.plan.extraProtein}</h5>
                  <h5> Extra Details: { x.plan.details}</h5>
                  <h5> Dislikes: {x.dislikesList.join(",")}
                  </h5>
                </div>

                <div style={{padding:'20px'}}>
                  <h4 style={{color:'#599BE9'}}>Meals </h4>
                  <h5> Name: name</h5>
                  <h5> Description: description</h5>
                  <h5> Name: name</h5>
                  <h5> Description: description</h5>
                  <h5> Name: name</h5>
                  <h5> Description: description</h5>
                </div>
              </CardBody>
            </Card>
            
            ) }
           
            
          </Container>
        );

    }
}

export default DisplayCombo;
