import React, { Component } from 'react';
import Retriever from '../middleware/Retriever';

import ApiToken from "../middleware/ApiToken";

import {
  Container,
  Table
} from 'reactstrap';

class AvailableMeals extends Component {


  constructor(props) {
    super(props);

    const apiToken = new ApiToken();
    if(!apiToken.isAuthenticated()){
      console.log('User Not Logged');
      this.props.history.push(`/Login`)
    }else{
      console.log('User Login Success');
    }

    this.state = {
    
      Meals: {}
    }

    
  }
  


  componentDidMount() {
    const retriever = new Retriever('api/meals/availablemeals');
    retriever.getEntityPromise()
      .then((obj) => {
        this.setState({ Meals: obj });
      })
  }
 
  Description() {
    var mealDescription = [];

    for (var i = 0; i < this.state.Meals.length; i++) {
      {
        mealDescription.push(this.state.Meals[i].description.toString());
      
      }
    }
    return mealDescription
  }

  Name() {
    var mealNames = [];

    for (var i = 0; i < this.state.Meals.length; i++) {
      {
        mealNames.push(this.state.Meals[i].name.toString());

      }
    }
    return mealNames
  }
 
  render () {
    var AvailableMealsDescription = this.Description();
    var AvailableMealsName = this.Name();
    
    return(
      <Container>
     
        <div> Number of available Meals <h4 style={{color:'blue'}}> {AvailableMealsName.length} </h4> </div>

        <div style={{ width:'700px', height:'300px', overflow:'scroll'}}>
          <Table striped bordered condensed >

            <thead>
            <th className="text-center">Name</th>
            <th className="text-center">Description</th>
            </thead>
            <tbody>  
            <td>
      {AvailableMealsName.map((item,index) =>
        
            <tr key={index}>
            {item}
          </tr>
        
      )}
            </td>
       
       <td>     
      {AvailableMealsDescription.map((item,index) =>
        
          <tr key={index}>
            {item}
          </tr>
        
      )}
      
       </td>
            </tbody>
          </Table>
       
      </div>
      </Container>
    );

  }


}
        


export default AvailableMeals;
