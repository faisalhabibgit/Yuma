import React, { Component } from 'react';
import {
  CardDeck,CardBody,Card,
  Button
} from 'reactstrap';
import Modal from 'react-modal';

class MealCombo extends Component {

  constructor() {
    super();

    this.state = {
      modalIsOpen: false,
      ModalContent:''
    };

    this.openModal = this.openModal.bind(this);
    this.closeModal = this.closeModal.bind(this);
    this.handleModalChange1 = this.handleModalChange1.bind(this);
    this.handleModalChange2 = this.handleModalChange2.bind(this);
    this.handleModalChange3 = this.handleModalChange3.bind(this);

  }

  openModal() {
    this.setState({modalIsOpen: true});
  }

  closeModal() {
    this.setState({modalIsOpen: false});
  }

  handleModalChange1() {
    this.openModal();
    this.setState({ ModalContent : <h1>Combo 1</h1>})
  }

  handleModalChange2() {
    this.openModal();
    this.setState({ ModalContent : <h1>Combo 2</h1>})
  }

  handleModalChange3() {
    this.openModal();
    this.setState({ ModalContent : <h1>Combo 3</h1>})
  }

  render() {
    return(
      <div>
        <CardDeck style={{padding:'15px', maxHeight:'200px'}}>

          <Card>
            <CardBody className="text-center">
              <h2> Meal Combo #1</h2>
              <div>
                <Button variant="secondary" onClick={this.handleModalChange1}>View Full Combination Report</Button>
              </div>
              <div style={{padding:'15px'}}>
                <Button style={{background: '#599BE9'}} type="submit">Select</Button>
              </div>
            </CardBody>
          </Card>

          <Card>
            <CardBody className="text-center">
              <h2> Meal Combo #2</h2>
              <div>
                <Button variant="secondary" onClick={this.handleModalChange2}>View Full Combination Report</Button>
              </div>
              <div style={{padding:'15px'}}>
                <Button style={{background: '#599BE9'}} type="submit">Select</Button>
              </div>
            </CardBody>
          </Card>

          <Card>
            <CardBody className="text-center">
              <h2> Meal Combo #3</h2>
              <div>
                <Button variant="secondary" onClick={this.handleModalChange3}>View Full Combination Report</Button>
              </div>
              <div style={{padding:'15px'}}>
                <Button style={{background: '#599BE9'}} type="submit">Select</Button>
              </div>
            </CardBody>
          </Card>

        </CardDeck>

        <Modal
          isOpen={this.state.modalIsOpen}
          onRequestClose={this.closeModal}
          style={{width:'1000px', height:'1000px'}}
        >
          <button onClick={this.closeModal}>close</button>
          <div>{ this.state.ModalContent}</div>
        </Modal>
      </div>
    );
  }
}

export default MealCombo;
