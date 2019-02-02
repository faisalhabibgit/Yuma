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
    const retriever = new Retriever('api/meals/all');
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
        
          <div style={{ maxWidth:'900px', maxHeight:'300px', overflow:'scroll'}}>
          <Table bordered condensed>
            <thead style={{background: '#599BE9',color:'white'}}>
            <th className="text-center" >Name</th>
            <th className="text-center" >Description</th>
            </thead>
            <tbody style={{background:'#d3d3d3'}}>
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
