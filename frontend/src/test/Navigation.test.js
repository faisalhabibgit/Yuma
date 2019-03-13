import React from 'react';
import { shallow } from 'enzyme';
import Navigation from '../component/Navigation';
import { BrowserRouter as Router } from "react-router-dom";

let wrapper;

beforeAll(() => {
    wrapper = shallow(<Router><Navigation /></Router>);
});

test('Has link to Logout page', () => {
    const linkString = '<a class="nav-link" href="/Logout">Logout</a>';
    expect(wrapper.html()).toContain(linkString);    
});