import React, { Component } from 'react';
import DisplayDelivery from './DisplayDelivery';
import {
  Container
} from 'reactstrap';


class MealDelivered extends Component {
  constructor(props) {
    console.log('Meals to be delivered this week');
    super(props);
    this.state = {
    }
  }

  getDisplay(){
    return <DisplayDelivery data={this.state.apiObject} />
  }

  render() {
    return (
      <Container>
        { this.getDisplay() }
      </Container>
    );

  }
}

export default MealDelivered;
