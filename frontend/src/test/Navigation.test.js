import React from 'react';
import { shallow } from 'enzyme';
import Navigation from '../component/Navigation';
import { BrowserRouter as Router } from "react-router-dom";

let wrapper;

beforeAll(() => {
    wrapper = shallow(<Router><Navigation /></Router>);
});

test('Has link to Login', () => {
    const Loginlink = wrapper.find("[data-test='navigation-login']");
    expect(Loginlink).toBeDefined;
});

test('Has link to Logout', () => {
    const Logoutlink = wrapper.find("[data-test='navigation-logout']");
    expect(Logoutlink).toBeDefined;
})



