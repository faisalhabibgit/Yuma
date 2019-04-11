import React, { Component } from 'react';
import {
    Container, Table, Col, Form,
    FormGroup, Label, Input,
    Button, Card, CardHeader,  CardBody,
    CardDeck
} from 'reactstrap';

import Retriever from '../middleware/Retriever';
import MealDelivered from './delivery/MealDelivered';
import Poster from "../middleware/Poster";
import Modal from 'react-modal';

class Delivery extends Component{

  constructor() {
    super();

    this.state = {
      modalIsOpen: false,
      ModalContent: '',
      hiddenElement: null
    };

    this.openModal = this.openModal.bind(this);
    this.closeModal = this.closeModal.bind(this);
    this.handleModalChange = this.handleModalChange.bind(this);

    this.poster = new Poster();
  }

  openModal() {
    this.setState({ modalIsOpen: true });
  }

  closeModal() {
    this.setState({ modalIsOpen: false });
  }

  handleModalChange() {
    this.openModal();
    this.setState({ ModalContent: <MealDelivered /> })
  }

render() {
      
    return(
      <Container>
       <label style={{ marginLeft: 10, color: '#599BE9', fontFamily: 'Comic Sans MS' }}> Checklist for Meal Delivery Service</label>
        <CardDeck style={{padding:'12px', height:'275px', maxWidth:'800px'}}>
          <Card>           
            <CardBody>
              <div>
                <Button onClick={this.handleModalChange} style={{ background: '#599BE9' }}>Delivery Service 1</Button>
              </div>
            </CardBody>
          </Card>
        </CardDeck>
        <Modal
          isOpen={this.state.modalIsOpen}
          onRequestClose={this.closeModal}
          style={{ width: '1000px', height: '1000px' }}
        >
        <button onClick={this.closeModal}>close</button>
        <div>{this.state.ModalContent}</div>
        </Modal>
      </Container>
  );
}

}
export default Delivery;
