import React, { Component } from 'react';
import { Link } from "react-router-dom";
import CustomLogging from "../CustomLogging";

import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink
} from 'reactstrap';

class Navigation extends Component {

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
            <Navbar color="light" light expand="md">
                <NavbarBrand href="/">Yuma</NavbarBrand>
                <NavbarToggler onClick={this.toggle} />
                <Collapse isOpen={this.state.isOpen} navbar>
                    <Nav className="ml-auto" navbar>
                        <NavItem>
                            <NavLink data-test="navigation-login" tag={Link} to="/Login">Login</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink data-test="navigation-logout" tag={Link} to="/Logout">Logout</NavLink>
                        </NavItem>
                    </Nav>
                </Collapse>
            </Navbar>
        );
    }
}

export default Navigation;
