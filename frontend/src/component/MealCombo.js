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
      modalIsOpen: false
    };

    this.openModal = this.openModal.bind(this);
    this.closeModal = this.closeModal.bind(this);
  }

  openModal() {
    this.setState({modalIsOpen: true});
  }

  closeModal() {
    this.setState({modalIsOpen: false});
  }

  render() {
    return(
      <div>
      <CardDeck style={{padding:'15px', maxHeight:'200px'}}>

        <Card>
          <CardBody className="text-center">
            <h2> Meal Combo #1</h2>
            <div>
              <Button variant="secondary" onClick={this.openModal}>View Full Combination Report</Button>
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
              <Button variant="secondary" onClick={this.openModal}>View Full Combination Report</Button>
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
              <Button variant="secondary" onClick={this.openModal}>View Full Combination Report</Button>
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
            <div>Content goes here</div>
          </Modal>
      </div>
    );
  }
}

export default MealCombo;
