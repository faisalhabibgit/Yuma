import React, { Component } from 'react';

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

    this.handlePassChange = this.handlePassChange.bind(this);
    this.handleConfirmPassword = this.handleConfirmPassword.bind(this);
    this.handleUserChange = this.handleUserChange.bind(this);
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.dismissError = this.dismissError.bind(this);
  }

  dismissError() {
    this.setState({ error: '' });
  }

  handleSubmit(evt) {
    evt.preventDefault();

    if (!this.state.username) {
      return this.setState({ error: 'Username is required' });
    }
    
    if (!this.state.email) {
      return this.setState({ error: 'Email is required' });
    }

    if (!this.state.password) {
      return this.setState({ error: 'Password is required' });
    }
    
    if (this.state.confirmPassword != this.state.password) {
      return this.setState({ error: 'Password does not match' });
    }

    return this.setState({ error: '' });
  }

  handleUserChange(evt) {
    this.setState({
      username: evt.target.value,
    });
  };
  
  handleEmailChange(evt) {
    this.setState({
      email: evt.target.value,
    });
  };

  handlePassChange(evt) {
    this.setState({
      password: evt.target.value,
    });
  };
  
  handleConfirmPassword(evt) {
    this.setState({
      confirmPassword: evt.target.value,
    });
  }

  render() {
    // NOTE: I use data-attributes for easier E2E testing
    // but you don't need to target those (any css-selector will work)

    return (
      <div className="Registration">
        <form onSubmit={this.handleSubmit}>
          {
            this.state.error &&
            <h3 data-test="error" onClick={this.dismissError}>
              <button onClick={this.dismissError}>✖</button>
              {this.state.error}
            </h3>
          }
          <label>User Name</label>
          <input type="text" data-test="username" value={this.state.username} onChange={this.handleUserChange} />
          <br/>
          <label>Email</label>
          <input type="email" data-test="email" value={this.state.email} onChange={this.handleEmailChange} />
          <br/>
          <label>Password</label>
          <input type="password" data-test="password" value={this.state.password} onChange={this.handlePassChange} />
          <br/>
          <label>Confirm Password</label>
          <input type="password" data-test="confirmPassword" value={this.state.confirmPassword} onChange={this.handleConfirmPassword} />
          <br/>
          <input type="submit" value="Register Now" data-test="submit" />
        </form>
      </div>
    );
  }
}

export default Registration;
