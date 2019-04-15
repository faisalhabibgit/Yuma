import React from 'react';
import { shallow } from 'enzyme';
import AllergiesChart from '../component/AllergiesChart';
import sinon from 'sinon';

let wrapper;

beforeAll(() => {
  //stub the componentDidMount method, as it causes a react error
  const cmDidMount = sinon.stub(AllergiesChart.prototype, 'componentDidMount').returns(true);
  wrapper = shallow(<AllergiesChart componentDidMount={cmDidMount}/>);

});

afterEach(() => {

  sinon.restore();

});

test('Allergies Chart renders pie chart', () => {

  expect(wrapper.length).toBe(1);

  const pieChart = wrapper.find("[data-test='pie-chart-allergies']");
  expect(pieChart.length).toBe(1);

});
