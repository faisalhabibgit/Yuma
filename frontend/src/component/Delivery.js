import React, { Component } from 'react';
import {
    Container,
    Button, Card,  CardBody,
    CardDeck
} from 'reactstrap';

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
      <div style={{padding:'25px'}}>
        <h2 style={{color: '#599BE9'}}>Checklist for Meal Delivery Service</h2>
      </div>
        <CardDeck style={{padding:'12px', height:'200px', maxWidth:'600px'}}>
          <Card>           
            <CardBody>
              <div>
                <Button onClick={this.handleModalChange} style={{ background: '#599BE9' }}> + Delivery Service 1</Button>
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
