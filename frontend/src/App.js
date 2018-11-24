import React, { Component } from 'react';
import { BrowserRouter, Route, Link } from "react-router-dom";

import LoginCaterer from './component/LoginCaterer';
import Registration from './component/Registration';
import ForgotPassword from './component/ForgotPassword';
import Navigation from './component/Navigation'

class App extends Component {
  
  render() {
    return (
      <div>
        <Navigation/>
      </div>
    );
  }
}

export default App;
