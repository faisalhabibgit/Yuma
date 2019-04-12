import React, {Component} from 'react';
import Retriever from "../middleware/Retriever";
import CustomLogging from "../CustomLogging";
import {Pie} from 'react-chartjs-2';

const dataArr=[];

class UsersByCompany extends Component {

  constructor(props) {
    super(props);
    this.state = {
      apiObject: [],
      dataArr: []
    }
  }
  componentDidMount() {
    const retriever = new Retriever('api/rest/listcompanies');
    CustomLogging.info('retrieving list of companies', 'UsersByCompany');
    retriever.getEntityPromise()
      .then((obj) => {
        // this gets the company list then finds the amount of user per company
        this.setState({apiObject: obj});
        
        // gets the data for every company in the company list
        for (var x = 0; x < this.state.apiObject.length; x++) {
          const retriever = new Retriever("api/rest/company/" + this.state.apiObject[x]);
          CustomLogging.info('retrieving company user list', 'UsersByCompany');
          retriever.getEntityPromise()
            .then((company) => {
              dataArr.push(company.length)
              this.setState({DataArr:dataArr})
            })
        }
      })
  }

  render() {
    
    var values = []
    for (var x in dataArr) {
      values.push(dataArr[x])
    }
    
    // dynamic colors for every company added to the pie chart
    var dynamicColors = function( i, total) {
      var r = 10+i* 15/total;
      var g = i* 200/total;
      var b = i* 400/total;
      return "rgb(" + r + "," + g + "," + b + ")";
    };
    
    var colors = [];

    for (var i in this.state.apiObject) {
      colors.push(dynamicColors(i, this.state.apiObject.length));
    }

    // data for the pie chart
    const data = {
        labels: this.state.apiObject,
        datasets: [{
        data: values,
        backgroundColor: colors,
      }]
    };

    return (
      <div>
        <h5 className='text-center'> Users By Company </h5>
        <Pie data-test="pie-chart" height="60px" data={data} />
      </div>

    );
  }
}

export default UsersByCompany;
