import React, { Component } from 'react';
import {
  Container, Card, CardBody, Table
} from 'reactstrap';
class DisplayDelivery extends Component {
  
  constructor(props) {
    super(props);

    this.state = {
      apiObject: this.props.data,
      displayLimit: 10,
      checkboxToggle: {}
    }
  }
  
  render() {
    return(
      <Container className="text-center">
        <h1>Delivery Checklist</h1>
        {this.state.apiObject.consumerList.map(x =>

          <div style={{ padding: '12px' }}>

            <Card style={{ background: '#d3d3d3' }}>
              <CardBody >
                <div style={{ padding:'12px', maxWidth:'1800px', maxHeight:'275px', overflow:'scroll'}}>
                  <Table bordered condensed>
                    <thead style={{background: '#599BE9',color:'white'}}>
                    <th className="text-center" >Name</th>
                    <th className="text-center" >Description </th>
                    <th className="text-center" >Delivery Status</th>
                    </thead>
                    <tbody style={{background:'#d3d3d3'}}>

                    {
                      x.mealList.map(x =>
                        <tr>
                          <td>{x.name}</td>
                          <td>{x.description.toString()}</td>
                          <td>  {
                            <label className="switch">
                              <input type="checkbox"/>
                              <span className="slider round"></span>
                            </label>
                          }</td>                        </tr>
                      )}
                    </tbody>
                  </Table>
                </div>
              </CardBody>
            </Card>
          </div>
        )}
      </Container>
    )
  }
}
  export default DisplayDelivery;
