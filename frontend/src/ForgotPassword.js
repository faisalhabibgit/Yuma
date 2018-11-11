import React, { Component } from 'react';

class ForgotPassword extends Component {
  
  constructor() {
    super();
    this.state = {
      email: '',
      error: '',
    };
    
    this.handleEmail = this.handleEmail.bind(this);
    this.dismissError = this.dismissError.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  
  dismissError() {
    this.setState({ error: '' });
  }
  
  handleSubmit(evt) {
    evt.preventDefault();
  
    if (!this.state.email) {
      return this.setState({ error: 'Email is required' });
    }
  
    return this.setState({ error: '' });
  }
  
  handleEmail(evt) {
    this.setState({
      email: evt.target.value,
    });
  }

  render() {
    return (
      <div className="Login">
        <h3>...so you couldn't even remember a password</h3>
       
        <form onSubmit={this.handleSubmit}>
           {
             this.state.error &&
            <h3 data-test="error" onClick={this.dismissError}>
              <button onClick={this.dismissError}>✖</button>
              {this.state.error}
            </h3>
           }
         
           <label>Your Email</label>
          <input type="email" data-test="email" value={this.state.email} onChange={this.handleEmail} />
         
           <br />
        
         
          <br />
          <input type="submit" value="Get Password" data-test="submit" />
        </form>
      </div>
    );
  }
}

export default ForgotPassword;
