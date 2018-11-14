import React, { Component } from 'react';
import logo from './yuma.svg';
import './App.css';

import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import LoginCaterer from './LoginCaterer';
import Registration from './Registration';
import Meal from './Meal';
import TestMeal from './TestMeal';

import ForgotPassword from './ForgotPassword';

// import NavBar from './NavBar/NavBar.js';

class App extends Component {
  render() {
    return (
       <Router>
        <div className="App">
          <div className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h2>Welcome to Yuma Ops!</h2>
          </div>

          {/*<div className="navbar">*/}
            {/*<Link className="navbutton" to="/">Home</Link>*/}
            {/*<Link className="navbutton" to="/LoginCaterer">Login</Link>*/}
            {/*<Link className="navbutton" to="/Registration">Registration</Link>*/}
          {/*</div>*/}
          {/*<hr />*/}

          {/*<Route exact path="/LoginCaterer" component={LoginCaterer} />*/}

          {/*<Route exact path="/ForgotPassword" component={ForgotPassword} />*/}
            <center>
              <h2>
                Please Identify Yourself:
              </h2>

              <div className="originInterface">
                <Link to="/LoginCaterer">
                <button className="buttonSize" type="button">
                  Caterer
                </button>
              </Link>
              <Link to="/LoginCaterer" class="size">
                <button className="buttonSize" type="button">
                  Customers
                </button>
              </Link>
                <Route exact path="/ForgotPassword" component={ForgotPassword} />
                <Route exact path="/Registration" component={Registration} />
                <Route exact path="/LoginCaterer" component={LoginCaterer} />
                <Route exact path="/meals" component={Meals} />
                <Route exact path="/mealsTest" component={TestMeal} />
              </div>
            </center>


        </div>
       </Router>
    );
  }
}

export default App;
