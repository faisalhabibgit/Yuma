import React, { Component } from 'react';
import Retriever from '../../middleware/Retriever';
import DisplayDelivery from './DisplayDelivery';
import Loading from '../Loading';
import {
  Container
} from 'reactstrap';

class MealDelivered extends Component {
  constructor(props) {
    console.log('Meals to be delivered this week');
    super(props);
    this.state = {
      apiObject: []
    }
  }

  componentDidMount() {
    console.log('component mounted');
    const retriever = new Retriever('api/combinationreport/weeklycombo');
    retriever.getEntityPromise()
      .then((obj) => {
        this.setState({
          apiObject: obj[0],
        });
      })
  }

  getDisplay(){
    if (this.state.apiObject === undefined || this.state.apiObject.length === 0) {
      console.log(this.state.apiObject);
      return <Loading />
    } else {
      return <DisplayDelivery data={this.state.apiObject} />
    }  }

  render() {
    return (
      <Container>
        { this.getDisplay() }
      </Container>
    );

  }
}

export default MealDelivered;
