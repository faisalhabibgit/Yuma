import React, { Component } from 'react';
import { CardDeck, CardBody, Card, Button} from 'reactstrap';
import Modal from 'react-modal';
import Poster from '../middleware/Poster';
import ComboOne from './mealcombo/ComboOne';
import Retriever from '../middleware/Retriever';
import CustomLogging from "../CustomLogging";
import './stylesheet/buttonColor.css';

class MealCombo extends Component {

  constructor() {
    super();

    this.state = {
      modalIsOpen: false,
      ModalContent: '',
      downloadCSV: '',
      hiddenElement: null,
      background:'#599BE9',
      
    };

    this.openModal = this.openModal.bind(this);
    this.closeModal = this.closeModal.bind(this);
    this.handleModalChange1 = this.handleModalChange1.bind(this);
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
    CustomLogging.info("Combo One content opened","MealCombo");
    this.setState({ ModalContent: <ComboOne /> })
  }
  
  
  handleSubmit(e) {
    CustomLogging.info("Selected Combo"+ e.target.id + "MealCombo");

      this.poster.selectMealCombo(e.target.index).then(() => {

      const retriever = new Retriever('api/combinationreport/download/consumers.csv');      
      retriever.getEntityPromise().then((csv) => {

        var link = document.createElement('a');
        link.href = 'data:text/csv;charset=utf-8,' + encodeURI(csv);
        link.target = '_blank';
        link.download = 'report.csv';  
             
        this.setState({ hiddenElement : link});
        this.setState({ downloadCSV: <Button style={{ background: '#599BE9', margin:'15px' }} 
        type="submit" onClick={this.handleDownloadCSV} >Download CSV</Button> })
      })
    })
}



  handleDownloadCSV() {

    CustomLogging.info("CSV downloaded","MealCombo");
    this.state.hiddenElement.click();

  }



  render() {
    return (
      <div>
        <CardDeck style={{ padding: '15px', maxHeight: '200px' }}>

          <Card body outline color="primary">
            <CardBody className="text-center">
              <h4> Weekly Meal Combination</h4>
              <div>
                <Button variant="secondary" onClick={this.handleModalChange1}>View Full Combination Report</Button>
              </div>
              <div style={{ padding: '15px' }}>
              <Button onClick= {this.handleSubmit} id='1' type="submit">Select </Button>
              </div> 
            </CardBody>
          </Card>
        </CardDeck>

        <Modal
          isOpen={this.state.modalIsOpen}
          onRequestClose={this.closeModal}
          style={{ width: '1000px', height: '1000px' }}
        >
          <button  onClick={this.closeModal}>close</button>
          <div>{this.state.ModalContent}</div>
        </Modal>
        <div>{this.state.downloadCSV}</div>
      </div>
    );
  }
}

export default MealCombo;
