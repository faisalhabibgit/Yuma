import React, { Component } from 'react';
import Retriever from '../middleware/Retriever';
import {
  ListGroup, ListGroupItem,
  Container, Col, Form, FormGroup, Label, Input
} from 'reactstrap';

import ApiToken from '../middleware/ApiToken';



class Test extends Component {

  constructor(props) {
    super(props);

    this.state = {
      value: '',
      limit: 10,
      apiObject: null,
      displayData: [],
    }


    this.checkAuthenticated();

    this.handleQueryChange = this.handleQueryChange.bind(this);
    this.handleNumber = this.handleNumber.bind(this);

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

  doSearch() {
    var matchedArr = [];
    var matchCount = 0;
    var limit = this.state.limit;

    for (var i = 0; i < this.state.apiObject.length; i++) {
      if (this.state.apiObject[i].description.toString().includes(this.state.value)
        && (matchCount < limit)
      ) {
        matchedArr.push(this.state.apiObject[i].description.toString());
        matchCount++;
      } else if (matchCount >= limit) {
        break;
      }
    }
    this.setState({ displayData: matchedArr });
  }

  render() {

    return (
      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <Form className="form">
            <FormGroup>
              <Label>Search Meals: </Label>
              <Input
                type="text"
                data-test="search-input"
                value={this.state.value}
                onChange={this.handleQueryChange}
              />
            </FormGroup>
            <FormGroup>
              <Label>Result Limit: </Label>
              <select value={this.state.limit} onChange={this.handleNumber}>
                <option value='5'>5</option>
                <option value='10'>10</option>
                <option value='20'>20</option>
                <option value='50'>50</option>
              </select>
            </FormGroup>
          </Form>
        </Col>
        <Col sm={{ size: 6, order: 2, offset: 1 }}>
          <ListGroup>
            {
              this.state.displayData.map(x => <ListGroupItem>{x}</ListGroupItem>)
            }
          </ListGroup>
        </Col>
      </Container>
    );
  }
}

export default Test;
