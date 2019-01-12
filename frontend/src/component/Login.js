import React, { Component } from 'react';
import {
  Col, Form,
  FormGroup, Label, Input,
  Button,
  Container,
} from 'reactstrap';

//const REDIRECTDASHBOARD='/';

class Login extends Component {
  
  constructor(props){
    super(props);
    this.state = {
      email:'',
      password:''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.postLogin = this.postLogin.bind(this);
  }
  
  handleChange(event){
    const target = event.target;
    const value = target.value;
    const id = target.id;

    this.setState({[id]: value});
  }
  
  handleSubmit(event){
    event.preventDefault();
    
    if(this.state.email.length<1){
      alert('Please enter an email address');
    } else if(this.state.password.length<1){
      alert('Please enter a password');
    }else{
      this.postLogin();
      //this.props.history.push(REDIRECTDASHBOARD);
    }
  }
  
  postLogin(){
    
    console.log(this.state.email+ ' '+this.state.password);
    //nathys add your stuff here
    
  }
  
  render() {
    return (
      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <h2>Sign In</h2>
          <Form className="form" onSubmit={this.handleSubmit}>
            <Col>
              <FormGroup>
                <Label>Email</Label>
                <Input
                  type="email"
                  name="email"
                  id="email"
                  placeholder="myemail@email.com"
                  onChange={this.handleChange}
                />
            
                <Label>Password</Label>
                <Input
                  type="password"
                  name="password"
                  id="password"
                  placeholder=""
                  onChange={this.handleChange}
                />
              </FormGroup>
            </Col>
            <Button type="submit" value="Submit">Submit</Button>
          </Form>
        </Col>
      </Container>

    );
  }
}

export default Login;
