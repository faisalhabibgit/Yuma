import React, {Component} from 'react';
import Retriever from "../middleware/Retriever";
import CustomLogging from "../CustomLogging";
import {Doughnut} from 'react-chartjs-2';


class MealsChart extends Component {

  constructor(props) {
    super(props);
    this.state = {
      allMeals: [],
      availableMeals: []
    }
  }

  componentDidMount() {
    const retrieverAll = new Retriever('api/meals/all');
    CustomLogging.info('retrieving all meals','DisplayMeals');
    retrieverAll.getEntityPromise()
      .then((obj) => {
      var matchedArr = [];
    for (let index = 0; index < obj.length; index++) {
      const element = obj[index];
      matchedArr.push(element);
    }
    this.setState({allMeals: matchedArr});
  })

    const retrieverAvail = new Retriever('api/meals/availablemeals');
    CustomLogging.info('retrieving all meals','DisplayMeals');
    retrieverAvail.getEntityPromise()
      .then((obj) => {
      var matchedArr = [];
    for (let index = 0; index < obj.length; index++) {
      const element = obj[index];
      matchedArr.push(element);
    }
    this.setState({availableMeals: matchedArr});
  })

  }

  render() {
    // calculate non Available Meals
    var nonAvailable = this.state.allMeals.length-this.state.availableMeals.length

    // data for the pie chart
    const data = {
      labels: ["Available", "NonAvailable"],
      datasets: [{
        data: [this.state.availableMeals.length, nonAvailable],
        backgroundColor:['#2E78C9', '#83AEDE']
      }]
    };

    return (
      <div>
      <h5 className='text-center'> Meals </h5>
      <Doughnut data-test="chart-meals" height="120px" data={data} />
    </div>

  );
  }
}

export default MealsChart;
  
