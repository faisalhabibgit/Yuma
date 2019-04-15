import React, {Component} from 'react';
import Retriever from "../middleware/Retriever";
import CustomLogging from "../CustomLogging";
import {Pie} from 'react-chartjs-2';

class AllergiesChart extends Component {

  constructor(props) {
    super(props);
    this.state = {
      allUsers: [],
      allergyDairy:"",
      allergyGluten:"",
      allergyPeanut:"",
      allergyShellfish:"",
      allergySoy:"",
      allergyTreeNuts:""
    }
  }

  componentDidMount() {
    var matchedCountDairy=0;
    var matchedCountGluten=0;
    var matchedCountPeanut=0;
    var matchedCountShellfish=0;
    var matchedCountSoy=0;
    var matchedCountTreeNuts=0;

    const retrieverAll = new Retriever('api/rest/all');
    CustomLogging.info('retrieving all users','AllergiesChart');
    retrieverAll.getEntityPromise()
      .then((obj) => {
        for (var i = 0; i < obj.length; i++) {
          for (var x = 0; x < obj[i].allergies.length; x++)
            if (obj[i].allergies[x].toString() === "DAIRY") {
              matchedCountDairy++
            } else if (obj[i].allergies[x].toString() === "GLUTEN") {
              matchedCountGluten++
            } else if (obj[i].allergies[x].toString() === "PEANUT") {
              matchedCountPeanut++
            } else if (obj[i].allergies[x].toString() === "SHELLFISH")
            {
              matchedCountShellfish++
            } else if (obj[i].allergies[x].toString() === "SOY")
            {
              matchedCountSoy++
            } else if (obj[i].allergies[x].toString() === "TREE_NUTS")
            {
              matchedCountTreeNuts++
            }
        }
        this.setState({allergyDairy:matchedCountDairy});
        this.setState({allergyGluten:matchedCountGluten});
        this.setState({allergyPeanut:matchedCountPeanut});
        this.setState({allergyShellfish:matchedCountShellfish});
        this.setState({allergySoy:matchedCountSoy});
        this.setState({allergyTreeNuts:matchedCountTreeNuts});
      })
  }

  render() {
    // data for the pie chart
    const data = {
      labels: ["Dairy","Gluten", "Peanut", "Shellfish","Soy","Tree Nuts"],
      datasets: [{
        data: [this.state.allergyDairy,this.state.allergyGluten,this.state.allergyPeanut,this.state.allergyShellfish,this.state.allergySoy,this.state.allergyTreeNuts],
        backgroundColor:['#2E78C9', '#83AEDE',"#194D33",'#3EC682','#C2C050','#42412E',]
      }]
    };
    return (
      <div>
        <h5 className='text-center'> Allergies </h5>
        <Pie data-test="pie-chart-allergies" height="200px" data={data} />
      </div>

    );
  }
}

export default AllergiesChart;
