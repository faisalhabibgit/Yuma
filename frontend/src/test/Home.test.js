import React from 'react';
import { shallow } from 'enzyme';
import Home from '../component/Home';
import sinon from 'sinon';

test('Home page renders', () => {
    
    //stub the authentication token
    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);
    
    expect(wrapper.length).toBe(1);
});

test('Home page renders meal card and button', () =>{


});