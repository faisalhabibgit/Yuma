import React, { Component } from 'react';

import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import LoginCaterer from './LoginCaterer';
import Registration from './Registration';
import ForgotPassword from './ForgotPassword';

class App extends Component {
  render() {
    return (
        <div className="App">
          <div className="App-header">
            <h2>Welcome to Yuma Ops!</h2>
          </div>
        </div>
    );
  }
}

export default App;
