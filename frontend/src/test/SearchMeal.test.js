import React from 'react';
import { shallow } from 'enzyme';
import SearchMeal from '../component/SearchMeal';
import sinon from 'sinon';

let wrapper;

beforeAll(() => {
    
    //stub the authentication token
    const stubber = sinon.stub(SearchMeal.prototype, 'checkAuthenticated').returns(true);

    //stub the componentDidMount method, as it causes a react error
    const cmDidMount = sinon.stub(SearchMeal.prototype, 'componentDidMount').returns(true);
    wrapper = shallow(<SearchMeal checkAuthenticated={stubber} componentDidMount={cmDidMount}/>);
    
});

afterAll(() => {

    sinon.restore();

});

test('Search meal page renders', () => {
    
    expect(wrapper.length).toBe(1);
});

test('Search meal renders search box', () => {
    
    expect(wrapper.length).toBe(1);
    
    //check if box renders
    const searchBox = wrapper.find("[data-test='search-input']");
    expect(searchBox.length).toBe(1);
    
});

test('Search meal page renders result limit select box', () =>{

    expect(wrapper.length).toBe(1);

    const selectBox = wrapper.find("[data-test='search-select']");
    expect(selectBox.length).toBe(1);

});

test('Search meal page renders meal list', () =>{

  expect(wrapper.length).toBe(1);

  const mealList = wrapper.find("[data-test='meal-list']");
  expect(mealList.length).toBe(1);

});

