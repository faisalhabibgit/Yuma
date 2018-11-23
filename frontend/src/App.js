import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem } from 'reactstrap';

import LoginCaterer from './component/LoginCaterer';
import Registration from './component/Registration';
import ForgotPassword from './component/ForgotPassword';

class App extends Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.state = {
      isOpen: false
    };
  }
  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }
  
  render() {
    return (
<Router>      
<html>
  <head>
    <link rel="stylesheet" href= "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </link>
  </head>
  <body>
    <Navbar color="light" light expand="md">
      <NavbarBrand href="/">Yuma</NavbarBrand>
      <NavbarToggler onClick={this.toggle} />
      <Collapse isOpen={this.state.isOpen} navbar>
        <Nav className="ml-auto" navbar>
          <NavItem>
            <NavLink tag={Link} to="/LoginCaterer">LoginCaterer</NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="https://github.com/faisalhabibgit/Yuma">GitHub</NavLink>
          </NavItem>
          <UncontrolledDropdown nav inNavbar>
            <DropdownToggle nav caret>
              Options
            </DropdownToggle>
            <DropdownMenu right>
              <DropdownItem>
                Option 1
              </DropdownItem>
              <DropdownItem>
                Option 2
              </DropdownItem>
              <DropdownItem divider />
              <DropdownItem>
                Reset
              </DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
        </Nav>
      </Collapse>
    </Navbar>
  </body>
  
  
    <Route exact path="/LoginCaterer" component={LoginCaterer}/>
    <Route path="/Registration" component={Registration}/>
</html>
</Router>



    );
  }
}

export default App;
