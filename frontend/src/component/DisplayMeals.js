import React, { Component } from 'react';
import {
  Container, Table
} from 'reactstrap';
import Retriever from '../middleware/Retriever';
import Poster from '../middleware/Poster';


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
        var checkToggleArr = {};
        for (let index = 0; index < this.state.apiObject.length; index++) {
          const element = this.state.apiObject[index].mealId.toString();
          const value = this.state.apiObject[index].isAvailable
          checkToggleArr[element] = value;
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
        this.state.checkboxToggle[toggleId] = ! this.state.checkboxToggle[toggleId]
        this.forceUpdate()
    });
    //console.log(this.state.checkboxToggle)
  }

  render() {
    return (

      <Container>
        <h5 className="text-center"> Number of All Meals:{this.state.apiObject.length} </h5>
        <div style={{ maxWidth: '900px', maxHeight: '300px', overflow: 'scroll' }}>
          <Table bordered condensed>
            <thead style={{ background: '#599BE9', color: 'white' }}>
              <th className="text-center" >Name</th>
              <th className="text-center" >Description</th>
            </thead>
            <tbody style={{ background: '#d3d3d3' }}>
              {
                this.state.apiObject.map(x =>
                  <tr>
                    <td>{x.name.toString()}</td>
                    <td>{x.description.toString()}</td>
                    <td>
                      {
                        <input
                          type="checkbox"
                          name={x.mealId.toString()}
                          checked={this.state.checkboxToggle[x.mealId.toString()]}
                          onClick={this.handleClick.bind(this)}
                        />
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
