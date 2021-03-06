import React, { Component } from 'react';
import './stylesheet/toggle.css'
import {
  Container, Table
} from 'reactstrap';
import Retriever from '../middleware/Retriever';
import Poster from '../middleware/Poster';
import CustomLogging from '../CustomLogging';

class DisplayMeals extends Component {

  constructor(props) {
    super(props);
    this.state = {
      apiObject: [],
      checkboxToggle: {}
    }
  }

  componentDidMount() {
    const retriever = new Retriever('api/meals/all');
    CustomLogging.info('retrieving all meals','DisplayMeals');
    retriever.getEntityPromise()
      .then((obj) => {
        var matchedArr = [];
        for (let index = 0; index < obj.length; index++) {
          const element = obj[index];
          matchedArr.push(element);
        }
        this.setState({ apiObject: matchedArr });
  
        // This stores the availability of the meals as
        // mealId => value inside checkboxToggle
        CustomLogging.info('checking availability of meals','DisplayMeals');
        var checkToggleArr = {};
        for (let index = 0; index < this.state.apiObject.length; index++) {
          const element = this.state.apiObject[index].mealId.toString();
          const value = this.state.apiObject[index].isAvailable
          checkToggleArr[element] = value;
          CustomLogging.info('Meal availability: ' + this.state.apiObject[index].mealId+': '+this.state.apiObject[index].isAvailable,'DisplayMeals');
        }
        this.setState({ checkboxToggle: checkToggleArr });
      })
  }

  handleClick(event) {
    //This saves the mealID inside a var: toggleID
    const toggleId = event.target.name

    const poster = new Poster();
    poster.toggleMeal(toggleId)
      .then((empty) => {

        // eslint-disable-next-line
        this.state.checkboxToggle[toggleId] = !this.state.checkboxToggle[toggleId]
        this.forceUpdate()
        CustomLogging.info("Updating meal availability of " +toggleId,"DisplayMeals")
      });
  }

  render() {
    return (
      <Container>
        <h5 className="text-center"> Number of All Meals:{' '+this.state.apiObject.length} </h5>
        <div style={{ maxWidth: '900px', maxHeight: '300px', overflow: 'scroll' }}>
          <Table bordered condensed>
            <thead style={{ background: '#599BE9', color: 'white' }}>
              <th className="text-center" >Name</th>
              <th className="text-center" >Description</th>
              <th className="text-center" >Availability</th>
            </thead>
            <tbody style={{ background: '#d3d3d3' }}>
              {
                this.state.apiObject.map(x =>
                  <tr>
                    <td>{x.name.toString()}</td>
                    <td>{x.description.toString()}</td>
                    <td>
                      {
                        <label className="switch">
                          <input
                            type="checkbox"
                            name={x.mealId.toString()}
                            checked={this.state.checkboxToggle[x.mealId.toString()]}
                            onClick={this.handleClick.bind(this)}
                          />
                          <span className="slider round"></span>
                        </label>
                      }
                    </td>
                  </tr>
                )}
            </tbody>
          </Table>
        </div>
      </Container>
    );
  }
}

export default DisplayMeals;
