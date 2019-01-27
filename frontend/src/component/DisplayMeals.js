import React, { Component } from 'react';
import {
    Container, Table
} from 'reactstrap';
import Retriever from '../middleware/Retriever';


class DisplayMeals extends Component {

  constructor(props) {
    super(props);
    this.state = {
      apiObject: [],
      displayLimit: 10,
    }
  }

  componentDidMount() {
    const retriever = new Retriever('api/meals');
    retriever.getEntityPromise()
      .then((obj) => {
        var matchedArr = [];

        for (let index = 0; index < this.state.displayLimit; index++) {
          const element = obj[index];
          matchedArr.push(element);
        }
        this.setState({ apiObject: matchedArr });
      })
  }

    render() {
        console.log(this.state.apiObject);
      return(
        
        <Container>
        
          <div style={{ maxWidth:'800px', maxHeight:'275px', overflow:'scroll'}}>
          <Table striped bordered condensed>
            <thead>
            <th className="text-center">Name</th>
            <th className="text-center">Description</th>
            </thead>
            <tbody >
            <td>
              {
                this.state.apiObject.map(x => <tr>{x.name.toString()}</tr>)
              }
            </td>
            <td>
              {
                this.state.apiObject.map(x => <tr>{x.description.toString()}</tr>)
              }
            </td>
            </tbody>
          </Table>
        </div>
      </Container>
    );

    }
}

export default DisplayMeals;
