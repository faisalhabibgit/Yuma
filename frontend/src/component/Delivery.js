import React, { Component } from 'react';
import {
    Container, Table, Col, Form,
    FormGroup, Label, Input,
    Button, Card, CardHeader,  CardBody,
    CardDeck
} from 'reactstrap';

import Retriever from '../middleware/Retriever';

class Delivery extends Component{

render() {
      
    return(
      <Container>
        <CardDeck style={{padding:'12px', height:'275px'}}>
          <Card>           
            <CardBody>
              <label style={{ marginLeft: 90 }}> Create a checklist for today's  meal delivery</label>
              <div>
                <Button onClick style={{ background: '#599BE9' }}>Create</Button>
              </div>
            </CardBody>
          </Card>
        </CardDeck>
      </Container>
  );
}

}
export default Delivery;
