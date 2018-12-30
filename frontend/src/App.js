import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";

import LoginCaterer from './component/LoginCaterer';
import Registration from './component/Registration';
import Navigation from './component/Navigation';
import Footer from './component/Footer';
import Error from './component/Error';
import Home from './component/Home';
import Test from './component/Test';
import NewMeal from './component/NewMeal';

class App extends Component {

  render() {
    return (
      <BrowserRouter>
        <div>
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"></link>
          <Navigation />
          <Switch>
          <Route path="/" component={Home} exact />
          <Route path="/LoginCaterer" component={LoginCaterer} />
          <Route path="/Registration" component={Registration} />
          <Route path="/NewMeal" component={NewMeal} />
          <Route path="/Test" component={Test} />
          <Route component={Error} />
          </Switch>
          <Footer/>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
