import React from 'react';
import { shallow } from 'enzyme';
import Navigation from '../component/Navigation';
import { NavLink, Link, BrowserRouter as Router } from "react-router-dom";

let wrapper;

beforeAll(() => {
    wrapper = shallow(<Router><Navigation /></Router>);
});

test('Has link to New Meals Page', () => {
    const linkString = '<a class="nav-link" href="/newMeal">New Meal</a>';
    expect(wrapper.html()).toContain(linkString);
});

test('Has link toward \'test\' page', () => {
    const linkString = '<a class="nav-link" href="/Test">Meals</a>';
    expect(wrapper.html()).toContain(linkString);
});

test('Has link to Logout page', () => {
    const linkString = '<a class="nav-link" href="/Logout">Logout</a>';
    expect(wrapper.html()).toContain(linkString);    
});