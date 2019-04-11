import React, { Component } from 'react';
import {
  Col, Form,
  FormGroup, Label, Input,
  Button,
  Container,
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import CustomLogging from '../CustomLogging';
//const REDIRECTDASHBOARD='/';

class Login extends Component {
  
  constructor(props){
    super(props);

    const apiToken = new ApiToken();
    if(!apiToken.isAuthenticated()){
      CustomLogging.error('User Not Logged','Login');
    }else{
      CustomLogging.info('User Login Success','Login');
      this.props.history.push(`/`)
    }

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
      CustomLogging.alert('No email added','Login');
    } else if(this.state.password.length<1){
      alert('Please enter a password');
      CustomLogging.alert('No password','Login');
    }else{
      this.postLogin();
      CustomLogging.info('logging in..','Login');
    }
  }
  
  postLogin(){
    const apiToken = new ApiToken();
    apiToken.getToken('api/auth/signin',
      {
        email: this.state.email,
        password: this.state.password
      })
      .then(msg => CustomLogging.info(msg,'Login'))
      .then(x => CustomLogging.info('Attempting to Redirect...','Login'))
      .then(y => this.props.history.push(`/`));
  }
  
  render() {
    return (
      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <br/>
          <h2 style={{textAlign:'center'}}>Sign In</h2>
          <Form className="form" onSubmit={this.handleSubmit}>
            <Col>
              <br/>
              <FormGroup>
                <Label>Email</Label>
                <Input
                  type="email"
                  name="email"
                  id="email"
                  placeholder="myemail@email.com"
                  onChange={this.handleChange}
                />
              <br/>
                <Label>Password</Label>
                <Input
                  type="password"
                  name="password"
                  id="password"
                  placeholder=""
                  onChange={this.handleChange}
                />
                <br/>
                <Button style={{textAlign: 'right',alignSelf: 'stretch'}}type="submit" value="Submit">Submit</Button>
              </FormGroup>
            </Col>
            <br/>
          </Form>
        </Col>
      </Container>

    );
  }
}

export default Login;
