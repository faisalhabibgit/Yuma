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
              {
                this.state.apiObject.mealsList.map(x =>
                  <tr>
                    <td>{x.name}</td>
                    <td>{x.description}</td>
                  </tr>
                ) }
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
                  <h6> Number of Meals: { x.plan.numOfMeals}</h6>
                  <h6> Extra Veggies: { x.plan.extraVeggies.toString()}</h6>
                  <h6 > Extra Protein: { x.plan.extraProtein.toString()}</h6>
                  <h6> Details: {x.plan.details.toString()}  </h6>
                  <h6> Dislikes: {x.dislikesList.join(",")}
                  </h6>
                </div>
                </div>
                 
                <div>
                  <h5 style={{color:'#599BE9'}}>Meals </h5>

                    {  x.mealList.map(meal =>
                    <h6> Name: {meal.name} , Description: {meal.description}</h6>
                    )}
                  </div>
              </CardBody>
            </Card>
             </div>
            )}
               
          </Container>
        );
    }
}

export default DisplayCombo;
