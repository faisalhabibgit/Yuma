import React from 'react';
import { shallow } from 'enzyme';
import Test from '../component/Test';
import sinon from 'sinon';

test('renders without crashing', () => {

    //stub the authentication token
    const stubber = sinon.stub(Test.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Test checkAuthenticated={stubber}/>);
    
    expect(wrapper.length).toBe(1);
    sinon.restore();

});

test('renders the search form to find meals', () => {

    const stubber = sinon.stub(Test.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Test checkAuthenticated={stubber}/>);
    
    expect(wrapper.length).toBe(1);
    sinon.restore();
});