import React, { Component } from 'react';
import {
  Col, Form,
  FormGroup, Label, Input,
  Button,
  Container,
} from 'reactstrap';

class Login extends Component {
  render() {
    return (
      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <h2>Sign In</h2>
          <Form className="form">
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
            <Button>Submit</Button>
          </Form>
        </Col>
      </Container>

    );
  }
}

export default Login;
