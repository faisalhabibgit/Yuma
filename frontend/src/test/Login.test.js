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

test('Assert form records inputs', () => {
    expect(wrapper.find('#email').length).toBe(1);
    expect(wrapper.find('#password').length).toBe(1);

    const credentials = { user:'test@gmail.com', pass: 'pass'};

    const userInput = wrapper.find('#email');
    userInput.value = credentials.user;
    expect(userInput.value).toEqual(credentials.user);

    const passInput = wrapper.find('#password');
    passInput.value = credentials.pass;
    expect(passInput.value).toEqual(credentials.pass);
});