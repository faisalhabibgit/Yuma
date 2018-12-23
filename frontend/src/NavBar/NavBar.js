import React, { Component } from 'react';
import { LoginModal } from './LoginModal.js';
import { RegisterModal } from './RegisterModal.js';
import Logout from './Logout.js';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import {Meals} from '../..Meal.js';


import { Link } from 'react-router-dom';


export default class NavBar extends Component{
  render(){
    return(
      <Navbar className="Navbar" inverse collapseOnSelect staticTop>
        <Navbar.Header>
          <Navbar.Brand>
            <Link to="/" className="NavbarLink">Yuma</Link>
          </Navbar.Brand>
          <Navbar.Toggle />
        </Navbar.Header>
        <Navbar.Collapse>
          <Nav>
            <NavItem eventKey={1}><Link className="NavbarLink" to="/">Home</Link></NavItem>
            // <NavItem eventKey={2}><Link className="NavbarLink" to="/aboutus">About us</Link></NavItem> // does not exist
            <NavItem eventKey={3}><Link className="NavbarLink" to="/meals">Meals</Link></NavItem>

          </Nav>
        </Navbar.Collapse>
      </Navbar>
    );
  }
}
