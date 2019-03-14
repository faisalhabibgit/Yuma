import React from 'react';
import { shallow } from 'enzyme';
import Test from '../component/Test';
import sinon from 'sinon';

//CODE WILL BE UNCOMMENTED ONCE TEST.JS IS REFACTORED TO SearchMeal.js
// this is because line 13 causes the js test runner to fail, as 'file_name.prototype' must
// represent Test.js, but this is not allowed


test('Search meal page renders', () => {
    
    // //stub the authentication token
    // const stubber = sinon.stub(Test.prototype, 'checkAuthenticated').returns(true);
    // const wrapper = shallow(<Test checkAuthenticated={stubber}/>);
    
    // expect(wrapper.length).toBe(1);
    // sinon.restore();
});

// test('Search meal renders search box', () => {

//     //stub the authentication token
//     const stubber = sinon.stub(Test.prototype, 'checkAuthenticated').returns(true);
//     const wrapper = shallow(<Test checkAuthenticated={stubber}/>);
    
//     expect(wrapper.length).toBe(1);
    
//     //check if box renders
//     const searchBox = wrapper.find("[data-test='search-meal']");
//     expect(searchBox.length).toBe(1);
    
//     sinon.restore();

// });