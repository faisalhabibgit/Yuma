import React, { Component } from 'react';
import {
  CardDeck, CardBody, Card,
  Button
} from 'reactstrap';
import Modal from 'react-modal';

import Poster from '../middleware/Poster';

import ComboOne from './mealcombo/ComboOne';
import ComboTwo from './mealcombo/ComboTwo';
import ComboThree from './mealcombo/ComboThree';

import Retriever from '../middleware/Retriever';

class MealCombo extends Component {

  constructor() {
    super();

    this.state = {
      modalIsOpen: false,
      ModalContent: '',
      downloadCSV: '',
      hiddenElement: null
    };

    this.openModal = this.openModal.bind(this);
    this.closeModal = this.closeModal.bind(this);
    this.handleModalChange1 = this.handleModalChange1.bind(this);
    this.handleModalChange2 = this.handleModalChange2.bind(this);
    this.handleModalChange3 = this.handleModalChange3.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleDownloadCSV = this.handleDownloadCSV.bind(this);

    this.poster = new Poster();
  }

  openModal() {
    this.setState({ modalIsOpen: true });
  }

  closeModal() {
    this.setState({ modalIsOpen: false });
  }

  handleModalChange1() {
    this.openModal();
    this.setState({ ModalContent: <ComboOne /> })
  }

  handleModalChange2() {
    this.openModal();
    this.setState({ ModalContent: <ComboTwo /> })
  }

  handleModalChange3() {
    this.openModal();
    this.setState({ ModalContent: <ComboThree /> })
  }

  handleSubmit(e) {
    this.poster.selectMealCombo(e.target.id).then((empty) => {
      const retriever = new Retriever('api/combinationreport/download/consumers.csv');
      retriever.getEntityPromise().then((csv) => {

        this.setState({ hiddenElement: document.createElement('a') });
        this.state.hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(csv);
        this.state.hiddenElement.target = '_blank';
        this.state.hiddenElement.download = 'report.csv';
        
        this.setState({ downloadCSV: <Button style={{ background: '#599BE9' }} type="submit" onClick={this.handleDownloadCSV} >Download CSV</Button> })
      })
    })
  }

  handleDownloadCSV() {
    this.state.hiddenElement.click();
  }



  render() {
    return (
      <div>
        <CardDeck style={{ padding: '15px', maxHeight: '200px' }}>

          <Card>
            <CardBody className="text-center">
              <h2> Meal Combo #1</h2>
              <div>
                <Button variant="secondary" onClick={this.handleModalChange1}>View Full Combination Report</Button>
              </div>
              <div style={{ padding: '15px' }}>
                <Button style={{ background: '#599BE9' }} onClick={this.handleSubmit} id='1' type="submit">Select</Button>
              </div>
            </CardBody>
          </Card>
          <Card>
            <CardBody className="text-center">
              <h2> Meal Combo #2</h2>
              <div>
                <Button variant="secondary" onClick={this.handleModalChange2}>View Full Combination Report</Button>
              </div>
              <div style={{ padding: '15px' }}>
                <Button style={{ background: '#599BE9' }} onClick={this.handleSubmit} id='2' type="submit">Select</Button>
              </div>
            </CardBody>
          </Card>

          <Card>
            <CardBody className="text-center">
              <h2> Meal Combo #3</h2>
              <div>
                <Button variant="secondary" onClick={this.handleModalChange3}>View Full Combination Report</Button>
              </div>
              <div style={{ padding: '15px' }}>
                <Button style={{ background: '#599BE9' }} onClick={this.handleSubmit} id='3' type="submit">Select</Button>
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
        <div>{this.state.downloadCSV}</div>
      </div>
    );
  }
}

export default MealCombo;
