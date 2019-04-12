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
   
    const data = {
      labels: this.state.apiObject,
      datasets: [{
        data: []
      }]
    };
      
      return (
        <div>
          <Pie height="50x" data={data} />
          {this.state.DataArr.length} 
          {this.state.companyLength.toString()}
        </div>
        
  );
  }
}

export default UsersByCompany;
