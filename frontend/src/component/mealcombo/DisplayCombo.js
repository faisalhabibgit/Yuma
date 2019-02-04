import React, { Component } from 'react';
import {
  Container, Card,CardBody, Table
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

            <h1>Meals</h1>
            <Table striped bordered size="sm">
              <thead>
              <tr>
                <th> <h5 style={{color:'#599BE9'}}> Name </h5> </th>
                <th> <h5 style={{color:'#599BE9'}}> Description </h5> </th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td> <h6> Name</h6></td>
                <td> <h6> Description</h6></td>
              </tr>
              </tbody>
            </Table> 
            
            <h1>Users</h1>
           
            { this.state.apiObject.consumerList.map(x => 
            
             <div style={{padding:'10px'}}> 
              
            <Card style={{background:'#d3d3d3'}}>
              
              <CardBody>
                
                <div class="row">
                <div class= "col" style={{padding:'20px'}}> <h5 style={{color:'#599BE9'}}> User Info </h5>
                <h6> First name: { x.firstName}</h6>
                <h6> Last Name: { x.lastName}</h6>
                <h6> Email: { x.email}</h6>
                </div>


                  <div className="col" style={{padding: '20px'}}>
                  <h5 style={{color:'#599BE9'}}>User Details </h5>
                  <h6> Number of Meals: { x.plan.numOfMeal}</h6>
                  <h6> Extra Veggies: { x.plan.extraVeggies}</h6>
                  <h6 > Extra Protein: { x.plan.extraProtein}</h6>
                  <h6> Details: {x.plan.details}  </h6>
                  <h6> Dislikes: {x.dislikesList.join(",")}
                  </h6>
                </div>
                </div>
                
                  <div>
                  <h5 style={{color:'#599BE9'}}>Meals </h5>
                    <h6> Name: name , Description: chicken and broccolie topped with a touch of love</h6>
                    <h6> Name: name , Description: chicken and broccolie topped with a touch of love</h6>
                    <h6> Name: name , Description: chicken and broccolie topped with a touch of love</h6>
                    <h6> Name: name , Description: chicken and broccolie topped with a touch of love</h6>
                    <h6> Name: name ,  Description: chicken and broccolie topped with a touch of love</h6>

                  </div>
              </CardBody>
            </Card>
             </div>
            ) }
               
          </Container>
        );

    }
}

export default DisplayCombo;
