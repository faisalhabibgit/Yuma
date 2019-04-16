import React, {Component} from 'react';
import Retriever from "../middleware/Retriever";
import CustomLogging from "../CustomLogging";
import {Pie} from 'react-chartjs-2';


class DietChart extends Component {

  constructor(props) {
    super(props);
    this.state = {
      allUsers: [],
      dietHalal : "",
      dietKosher:"",
      dietNone: "",
    }
  }

  componentDidMount() {
    var matchedCountHalal= 0;
    var matchedCountKosher= 0;
    var matchedCountNone= 0;
    const retrieverAll = new Retriever('api/rest/all');
    CustomLogging.info('retrieving all users','DietChart');
    retrieverAll.getEntityPromise()
      .then((obj) => {

        // get count of diets
    for (var i = 0; i < obj.length; i++)
    {   
      if (obj[i].plan.diet.toString() === "NONE")
      {
        matchedCountNone++
      } else if (obj[i].plan.diet.toString() === "HALAL")
      {
        matchedCountHalal++
      }else if (obj[i].plan.diet.toString() === "KOSHER")
      {
        matchedCountKosher++
      }
    }
    this.setState({dietHalal:matchedCountHalal});
    this.setState({dietKosher:matchedCountKosher});
    this.setState({dietNone:matchedCountNone})
      })
  }

  render() {
    // data for the pie chart
    const data = {
      labels: ["Halal","Kosher", "None"],
      datasets: [{
        data: [this.state.dietHalal, this.state.dietKosher, this.state.dietNone],
        backgroundColor:['#2E78C9', '#83AEDE',"#194D33"]
      }]
    };
    return (
      <div>
        <h5 className='text-center'> Diet </h5>
        <Pie data-test="pie-chart-diet" height="200px" data={data} />
      </div>

    );
  }
}

export default DietChart;
  
