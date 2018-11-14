import React, { Component } from 'react';

import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import LoginCaterer from './LoginCaterer';
import Registration from './Registration';
import ForgotPassword from './ForgotPassword';
import App from './App';

class Routes extends Component {
  render() {
    return (
      <Router>
        <Route exact path="/ForgotPassword" component={ForgotPassword} />
        <Route exact path="/Registration" component={Registration} />
        <Route exact path="/LoginCaterer" component={LoginCaterer} />
      </Router>
    );
  }
}

export default App;
