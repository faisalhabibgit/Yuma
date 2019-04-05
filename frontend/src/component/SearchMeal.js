import React, { Component } from 'react';
import Retriever from '../middleware/Retriever';
import {
  ListGroup, ListGroupItem,
  Container, Col, Form, FormGroup, Label, Input, Collapse, Button
} from 'reactstrap';

import ApiToken from '../middleware/ApiToken';
import search from './images/background.png';

var sectionStyle = {
  width: "100%",
  height: "600px",
  backgroundImage: `url(${search})`,
  backgroundRepeat: 'noRepeat',
};

class SearchMeal extends Component {

  constructor(props) {
    super(props);

    this.checkAuthenticated();

    this.state = {
      value: '',
      limit: 10,
      apiObject: null,
      displayData: [],
      displayIng:[],
      collapse: false
    }

    this.handleQueryChange = this.handleQueryChange.bind(this);
    this.handleNumber = this.handleNumber.bind(this);
    this.toggle = this.toggle.bind(this);
  }

  checkAuthenticated(){
    const apiToken = new ApiToken();
    if(!apiToken.isAuthenticated()){
      console.log('User Not Logged');
      this.props.history.push(`/Login`)
    }else{
      console.log('User Login Success');
    }
  }

  componentDidMount() {
    window.scrollTo(0, 0);
    const retriever = new Retriever('api/meals/all');
    retriever.getEntityPromise()
      .then((obj) => {
        this.setState({ apiObject: obj });
      })
  }

  handleQueryChange(event) {
    this.setState({ value: event.target.value });
    this.doSearch();
  }

  handleNumber(event) {
    this.setState({ limit: event.target.value });
  }

  toggle(x) {
    this.setState({ collapse: this.state.collapse === x ? null : x });
  };

  doSearch() {
    var matchedArr = [];
    var matchCount = 0;
    var limit = this.state.limit;

    for (var i = 0; i < this.state.apiObject.length; i++) {
      if (this.state.apiObject[i].name.toString().includes(this.state.value)
        && (matchCount < limit)
      ) {
        matchedArr.push(this.state.apiObject[i]);
        matchCount++;
      } else if (matchCount >= limit) {
        break;
      }
    }
    this.setState({ displayData: matchedArr });
  }
  
  render() {
    return (
      <div className="text-center" style={ sectionStyle }  >
        <Container>
          <Col sm="12" md={{ size: 6, offset: 3 }}>
            <Form className="form">
              <FormGroup>
                <h1>Search the Meal Inventory </h1>
                <Input
                  type="text"
                  data-test="search-input"
                  value={this.state.value}
                  onChange={this.handleQueryChange}
                />
              </FormGroup>
              <FormGroup>
                <Label>Result Limit: </Label>
                <select value={this.state.limit} onChange={this.handleNumber} data-test="search-select">
                  <option value='5'>5</option>
                  <option value='10'>10</option>
                  <option value='20'>20</option>
                  <option value='50'>50</option>
                </select>
              </FormGroup>
            </Form>
          </Col>
          <div  style = {{height:'400px',overflow:'auto'}}>
            <Col sm="12" md={{ size: 6, offset: 3 }}>
              <ListGroup data-test='meal-list' >
                {
                  this.state.displayData.map(x => <ListGroupItem data-test='meal-item' >
                    <div>
                      <h5 style={{color: '#599BE9'}}>{x.name.toString()} </h5>
                    </div>
                    <div>
                      <Button onClick={this.toggle.bind(this,x)} style={{ background: '#599BE9' }}>+</Button>
                    </div>
                    <Collapse  isOpen={this.state.collapse===x}>
                      <div data-test='meal-description'>
                        <h5 style={{color: '#599BE9'}}>Description</h5> {x.description.toString()}
                      </div>
                      <div>
                        <h5 style={{color: '#599BE9'}}>Availability</h5>{x.isAvailable.toString()}
                      </div>
                    
                      <div>
                        <h5 style={{color: '#599BE9'}}>Ingredients </h5>
                        <ListGroup>{
                        x.ingredients.map(ing=> <ListGroupItem >

                          <h6 style={{color: '#599BE9'}}>Name</h6> {ing.name.toString()}
                          <h6 style={{color: '#599BE9'}}>Weight</h6> {ing.weight.toString()}
                          <h6 style={{color: '#599BE9'}}>Calories</h6>{ing.calories.toString()}
                          <h6 style={{color: '#599BE9'}}>Price</h6> {ing.price.toString()} 
                      
                        </ListGroupItem>)
                        }
                        </ListGroup>
                      </div>
                    </Collapse>
                  </ListGroupItem>)
                }
              </ListGroup>
            </Col>
          </div>
        </Container>
      </div>
    );
  }
}

export default SearchMeal;
