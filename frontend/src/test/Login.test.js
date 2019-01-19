import React from 'react';
import { shallow } from 'enzyme';
import Login from '../component/Login';

let wrapper;

beforeAll(() => {
    wrapper = shallow(<Login />);
});

test('Assert Correct Header', () => {
    const header =<h2 style={{textAlign:'center'}}>Sign In</h2>;
    expect(wrapper.contains(header)).toEqual(true);
});

test('Assert form exists', () => {
    //console.log(wrapper.html());
    expect(wrapper.exists('.form')).toBe(true);
});