import React, { Component } from 'react';
import {
  Container, Table
} from 'reactstrap';
import Retriever from '../middleware/Retriever';


class DisplayMeals extends Component {

  constructor(props) {
    super(props);
    this.state = {
      apiObject: []
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
      })
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
                    {/* <td></td> */}
                    <td>
                      {/* TODO: find some way to implement the boolean value of whether a meal is available */}
                      <input type="checkbox" name="{x.mealId.toString()}" checked='checked' />
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
