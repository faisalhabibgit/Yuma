import React from 'react';
import { shallow } from 'enzyme';
import Dashboard from '../component/Dashboard';
import { BrowserRouter as Router } from "react-router-dom";
import ApiToken from "../middleware/ApiToken";

let wrapper;

beforeAll(() => {
    wrapper = shallow(<Router><Dashboard /></Router>);
});

test('Renders correctly in debug', () =>{
    expect(wrapper).toMatchSnapshot();
});