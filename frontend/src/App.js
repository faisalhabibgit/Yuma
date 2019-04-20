import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";

import Login from './component/Login';
import Logout from './component/Logout';
import Registration from './component/Registration';
import Navigation from './component/Navigation';
import Footer from './component/Footer';
import Home from './component/Home';
import Error from './component/Error';
import SearchMeal from './component/SearchMeal';
import NewMeal from './component/NewMeal';
import MealHistory from './component/MealHistory';
import TestApiView from './component/TestApiView';
import Dashboard from './component/Dashboard';
import UserByCompanyName from './component/UserByCompanyName';


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
          <Route path="/SearchMeal" component={SearchMeal} />
          <Route path="/MealHistory" component={MealHistory}/>
          <Route path="/TestApiView" component={TestApiView} />
          <Route path="/Logout" component={Logout} />
          <Route path="/Dashboard" component={Dashboard} />
          <Route path="/UserByCompanyName" component={UserByCompanyName} />
          <Route component={Error} />
          </Switch>
          <Footer/>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
