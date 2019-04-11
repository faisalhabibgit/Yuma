import React, { Component } from 'react';
import {
  Container, Col, Form,
  FormGroup, Label, Input,
  Button,
} from 'reactstrap';


class Registration extends Component {
  constructor() {
    super();
    this.state = {
      username: '',
      email: '',
      password: '',
      confirmPassword: '',
      error: '',
    };
  }

    render() {
      return (
        <Container>
          <Col sm="12" md={{ size: 6, offset: 3 }}>
            <h2>Registration</h2>
            <Form className="form">
            <Col >
                <FormGroup>
                  <Label>User Name</Label>
                  <Input
                    type="username"
                    name="username"
                    id="exampleUsername"
                    placeholder="JohnDoe"
                  />
                </FormGroup>
              </Col>
              <Col >
                <FormGroup>
                  <Label>Email</Label>
                  <Input
                    type="email"
                    name="email"
                    id="exampleEmail"
                    placeholder="myemail@email.com"
                  />
                </FormGroup>
              </Col>
              <Col>
                <FormGroup>
                  <Label for="examplePassword">Password</Label>
                  <Input
                    type="password"
                    name="password"
                    id="examplePassword"
                    placeholder="********"
                  />
                </FormGroup>
              </Col>
              <Col>
                <FormGroup>
                  <Label for="examplePassword">Confirm Password</Label>
                  <Input
                    type="password"
                    name="confirmPassword"
                    id="examplePassword"
                    placeholder="********"
                  />
                </FormGroup>
              </Col>
              <Button>Submit</Button>
            </Form>
          </Col>
        </Container>
      );
    }
}
  export default Registration;
