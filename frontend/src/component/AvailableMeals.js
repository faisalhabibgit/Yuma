import React, { Component } from 'react';
import Retriever from '../middleware/Retriever';


import {
  Container,
  Table
} from 'reactstrap';

class AvailableMeals extends Component {
  
  constructor(props) {
    super(props);
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
      mealDescription.push(this.state.Meals[i].description.toString());
    }
    return mealDescription
  }

  Name() {
    var mealNames = [];
    for (var i = 0; i < this.state.Meals.length; i++) {
      mealNames.push(this.state.Meals[i].name.toString());
    }
    return mealNames
  }

  render () {
    var AvailableMealsDescription = this.Description();
    var AvailableMealsName = this.Name();

    return(
      <Container>

        <h4> Number of Available Meals: {AvailableMealsName.length} </h4>

        <div style={{ maxWidth:'800px', maxHeight:'275px', overflow:'scroll'}}>
          <Table bordered condensed>
            <thead style={{background: '#599BE9',color:'white'}}>
            <th className="text-center" >Name</th>
            <th className="text-center">Description</th>
            </thead>
            <tbody style={{background:'#d3d3d3'}} >
            <td>
              {AvailableMealsName.map((item,index) =>
                <tr key={index}>
                  {item}
                </tr>
              )}
            </td>
            <td>
              {AvailableMealsDescription.map((item,index) =>
                <tr key={index} >
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
