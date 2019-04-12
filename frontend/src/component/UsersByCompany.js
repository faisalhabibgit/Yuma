import React, {Component} from 'react';
import Retriever from "../middleware/Retriever";
import CustomLogging from "../CustomLogging";
import {Pie} from 'react-chartjs-2';

class UsersByCompany extends Component {

  constructor(props) {
    super(props);
    this.state = {
      apiObject: [],
      companyLength: [],
      DataArr:[]
    }
  }

  componentDidMount() {
    var DataArr = [];
    const retriever = new Retriever('api/rest/listcompanies');
    CustomLogging.info('retrieving list of companies', 'UsersByCompany');
    retriever.getEntityPromise()
      .then((obj) => {
        this.setState({apiObject: obj});
          
        var company = obj[0];
          const retriever = new Retriever("api/rest/company/" + company);
          CustomLogging.info('retrieving company user list', 'UsersByCompany');
          retriever.getEntityPromise()
            .then((obj1) => {
              this.setState({DataArr: obj1});

              DataArr.push(this.state.DataArr.length)
              this.setState({companyLength: DataArr});
            })
      }) 
  
  }

  render() {
    
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
    
    const data = {
      labels: this.state.apiObject,
      datasets: [{
        data: [1,2,2,1,1],
        backgroundColor: colors,

      }]
    };
      
      return (
        <div>
          <h5 className='text-center'> Users By Company </h5>
          <Pie height="60px" data={data} />
          {this.state.DataArr.length}
          {this.state.companyLength.toString()}
        </div>
        
  );
  }
}

export default UsersByCompany;
