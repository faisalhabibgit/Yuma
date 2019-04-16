import React, {Component} from 'react';
import Retriever from "../middleware/Retriever";
import CustomLogging from "../CustomLogging";
import {Pie} from 'react-chartjs-2';


class ProteinTypesChart extends Component {

  constructor(props) {
    super(props);
    this.state = {
      allUsers: [],
      proteinBeef:"",
      proteinLamb : "",
      proteinFish : "",
      proteinPlant : "",
      proteinPork : "",
      proteinPoultry : "",
      proteinShellfish : "",
      proteinVegan : "",
      proteinVegetarian : "",

    }
  }

  componentDidMount() {
    var matchedCountLamb=0;
    var matchedCountBeef=0;
    var matchedCountFish=0;
    var matchedCountPlant=0;
    var matchedCountPork=0;
    var matchedCountPoultry=0;
    var matchedCountShellfish=0;
    var matchedCountVegan=0;
    var matchedCountVegetarian=0;
    const retrieverAll = new Retriever('api/rest/all');
    CustomLogging.info('retrieving all users','ProteinTypesChart');
    retrieverAll.getEntityPromise()
      .then((obj) => {
        
        // get count of protein types
        for (var i = 0; i < obj.length; i++) {
          for (var x = 0; x < obj[i].plan.requestedProteinTypes.length; x++)
            if (obj[i].plan.requestedProteinTypes[x].toString() === "LAMB") {
              matchedCountLamb++
            } else if (obj[i].plan.requestedProteinTypes[x].toString() === "BEEF") {
              matchedCountBeef++
            } else if (obj[i].plan.requestedProteinTypes[x].toString() === "FISH") {
              matchedCountFish++
            } else if (obj[i].plan.requestedProteinTypes[x].toString() === "PLANT_BASED_PROTEIN")
            {
              matchedCountPlant++
            } else if (obj[i].plan.requestedProteinTypes[x].toString() === "PORK")
            {
              matchedCountPork++
            } else if (obj[i].plan.requestedProteinTypes[x].toString() === "POULTRY")
            {
              matchedCountPoultry++
            } else if (obj[i].plan.requestedProteinTypes[x].toString() === "SHELLFISH")
            {
              matchedCountShellfish++
            } else if (obj[i].plan.requestedProteinTypes[x].toString() === "VEGAN")
            {
              matchedCountVegan++
            } else if (obj[i].plan.requestedProteinTypes[x].toString() === "VEGETARIAN")
            {
              matchedCountVegetarian++
            }
        }
        this.setState({proteinLamb:matchedCountLamb});
        this.setState({proteinBeef:matchedCountBeef});
        this.setState({proteinFish:matchedCountFish});
        this.setState({proteinPork:matchedCountPork});
        this.setState({proteinPlant:matchedCountPlant});
        this.setState({proteinPoultry:matchedCountPoultry});
        this.setState({proteinShellfish:matchedCountShellfish});
        this.setState({proteinVegan:matchedCountVegan});
        this.setState({proteinVegetarian:matchedCountVegetarian});
      })
  }

  render() {
    // data for the pie chart
    const data = {
      labels: ["Lamb","Beef", "Fish", "Plant Based Diet","Pork","Poultry","Shellfish","Vegan","Vegetarian"],
      datasets: [{
        data: [this.state.proteinLamb,this.state.proteinBeef,this.state.proteinFish,this.state.proteinPlant,this.state.proteinPork,
          this.state.proteinPoultry,this.state.proteinShellfish,this.state.proteinVegan,this.state.proteinVegetarian],
        backgroundColor:['#2E78C9', '#83AEDE',"#194D33",'#3EC682','#C2C050','#42412E','#B95A34','#971EAB','#EBEBEB']
      }]
    };
    return (
      <div>
        <h5 className='text-center'>Requested Protein Types </h5>
        <Pie data-test="pie-chart-protein" height="200px" data={data} />
      </div>

    );
  }
}

export default ProteinTypesChart;
