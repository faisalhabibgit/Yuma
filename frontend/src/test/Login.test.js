import React from 'react';
import { shallow } from 'enzyme';
import Login from '../component/Login';
import sinon from 'sinon';

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

test('Assert onChange called when form receives username input', () =>{

    const spy = sinon.spy(Login.prototype, "handleChange");
    const event = {target: {name: "email", value: "test@gmail.com"}};
    const wrapperLogin = shallow(<Login handleChange={spy}/>);

    wrapperLogin.find('#email').simulate('change', event);

    expect(spy.calledOnce).toEqual(true);

    sinon.restore(); //restore handleChange
});

test('Assert onChange called when form receives password input', () =>{

    const spy = sinon.spy(Login.prototype, "handleChange");
    const event = {target: {name: "password", value: "password2"}};
    const wrapperLogout = shallow(<Login handleChange={spy}/>);

    wrapperLogout.find('#password').simulate('change', event);

    expect(spy.calledOnce).toEqual(true);

    sinon.restore(); //restore handleChange
});