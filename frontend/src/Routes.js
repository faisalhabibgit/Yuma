import React, { Component } from 'react';

import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import LoginCaterer from './component/LoginCaterer';
import Registration from './component/Registration';
import ForgotPassword from './component/ForgotPassword';
import App from './component/App';

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
