import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";

import Login from './component/Login';
import Registration from './component/Registration';
import Navigation from './component/Navigation';
import Footer from './component/Footer';
import Error from './component/Error';
import Home from './component/Home';
import Test from './component/Test';
import NewMeal from './component/NewMeal';
import TestApiView from './component/TestApiView';

class App extends Component {

  render() {
    return (
      <BrowserRouter>
        <div>
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"></link>
          <Navigation />
          <Switch>
          <Route path="/" component={Home} exact />
          <Route path="/Login" component={Login} />
          <Route path="/Registration" component={Registration} />
          <Route path="/NewMeal" component={NewMeal} />
          <Route path="/Test" component={Test} />
          <Route path="/TestApiView" component={TestApiView} />
          <Route component={Error} />
          </Switch>
          <Footer/>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
