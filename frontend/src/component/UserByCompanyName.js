import React, { Component } from 'react';
import {
  ListGroup, ListGroupItem,
  Container, Col, Form, FormGroup, Label, Input
} from 'reactstrap';


class UserByCompanyName extends Component {

  constructor(props) {
    super(props);

    this.state = {
      userInput: "",
      searchList: ["mock list",
                  "some stuff"]
    }

    this.handleQueryChange = this.handleQueryChange.bind(this);
  }

  handleQueryChange(input){
    this.setState({
      userInput: input
    }, ()=>console.log(input))
  }

  render() {
    return (
      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <Form className="form">
            <FormGroup>
              <input
                onChange = { (e)=>this.handleQueryChange(e.target.value)}
                value={this.state.userInput}
                type="text"
              />
            </FormGroup>
          </Form>
        </Col>
        <Col sm={{ size: 6, order: 2, offset: 1 }}>
          <ListGroup>
            {
              this.state.searchList.map(x => <ListGroupItem>{x}</ListGroupItem>)
            }
          </ListGroup>
        </Col>
      </Container>

    );
  }

}
export default UserByCompanyName;
