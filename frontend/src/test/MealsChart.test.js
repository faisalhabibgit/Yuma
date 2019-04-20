import React from 'react';
import { shallow } from 'enzyme';
import MealChart from '../component/MealChart';
import sinon from 'sinon';

let wrapper;

beforeAll(() => {
  //stub the componentDidMount method, as it causes a react error
  const cmDidMount = sinon.stub(MealChart.prototype, 'componentDidMount').returns(true);
wrapper = shallow(<MealChart componentDidMount={cmDidMount}/>);

});

afterEach(() => {

  sinon.restore();

});

test('Meal Chart renders pie chart', () => {

  expect(wrapper.length).toBe(1);

const pieChart = wrapper.find("[data-test='chart-meals']");
expect(pieChart.length).toBe(1);

});
