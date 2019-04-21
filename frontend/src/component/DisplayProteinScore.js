import React, { Component } from 'react';
import {
  Container, Table
} from 'reactstrap';
import Retriever from '../middleware/Retriever';
import CustomLogging from '../CustomLogging';

class DisplayProteinScore extends Component {

  constructor(props) {
    super(props);
    this.state = {
      apiObject: [],
      displayData: [],
      firstLetter : 'all',
      lastLetter : 'all',
    }
  }

  componentDidMount() {
    const retriever = new Retriever('api/score/proteinType/percentage');
    CustomLogging.info('retrieving Protein Type Score','DisplayProteinScore');
    retriever.getEntityPromise()
      .then((obj) => {
        var matchedArr = [];
        const element = obj;
        matchedArr.push(element);
        this.setState({ apiObject: matchedArr });
        this.setState({ displayData: matchedArr });
      })
  }
  
  
  render() {
    console.log(this.state.apiObject[0]);
    return(

      <Container>
        <div style={{ padding:'12px', maxWidth:'1800px', maxHeight:'275px', overflow:'scroll'}}>
          <Table bordered condensed>
            <thead style={{background: '#599BE9',color:'white'}}>
            <th className="text-center" >ProteinT Type</th>
            <th className="text-center" >Score </th>
            </thead>
            <tbody style={{background:'#d3d3d3'}}>
            {
              this.state.apiObject.map(x =>
                <tr>
                  <td> BEEF </td>
                  <td>{x[0].BEEF.toString()}</td>
                </tr>
              )}
            {
              this.state.apiObject.map(x =>
                <tr>
                  <td> FISH </td>
                  <td>{x[0].FISH.toString()}</td>
                </tr>
              )}
            {
              this.state.apiObject.map(x =>
                <tr>
                  <td> LAMB </td>
                  <td>{x[0].LAMB.toString()}</td>
                </tr>
              )}
            {
              this.state.apiObject.map(x =>
                <tr>
                  <td> POULTRY </td>
                  <td>{x[0].POULTRY.toString()}</td>
                </tr>
              )}
            </tbody>
          </Table>
        </div>
      </Container>
    );

  }
}

export default DisplayProteinScore;
