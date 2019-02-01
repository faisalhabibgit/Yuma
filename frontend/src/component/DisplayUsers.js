import React, { Component } from 'react';
import {
    Container, Table
} from 'reactstrap';
import Retriever from '../middleware/Retriever';


class DisplayUsers extends Component {

  constructor(props) {
    super(props);
    this.state = {
      apiObject: [],
      displayLimit: 10,
    }
  }

  componentDidMount() {
    const retriever = new Retriever('api/rest/all');
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
      return(
        
        <Container>
        
          <div style={{ maxWidth:'800px', maxHeight:'275px', overflow:'scroll'}}>
          <Table striped bordered condensed>
            <thead>
            <th className="text-center">First Name</th>
            <th className="text-center">Last Name</th>
            </thead>
            <tbody >
            <td>
              {
                this.state.apiObject.map(x => <tr>{x.firstName.toString()}</tr>)
              }
            </td>
            <td>
              {
                this.state.apiObject.map(x => <tr>{x.lastName.toString()}</tr>)
              }
            </td>
            </tbody>
          </Table>
        </div>
      </Container>
    );

    }
}

export default DisplayUsers;
