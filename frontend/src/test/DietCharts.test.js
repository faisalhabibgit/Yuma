import React from 'react';
import { shallow } from 'enzyme';
import DietChart from '../component/DietChart';
import sinon from 'sinon';

let wrapper;

beforeAll(() => {
  //stub the componentDidMount method, as it causes a react error
  const cmDidMount = sinon.stub(DietChart.prototype, 'componentDidMount').returns(true);
  wrapper = shallow(<DietChart componentDidMount={cmDidMount}/>);

});

afterEach(() => {

  sinon.restore();

});

test('Diet Chart renders pie chart', () => {

  expect(wrapper.length).toBe(1);

  const pieChart = wrapper.find("[data-test='pie-chart-diet']");
  expect(pieChart.length).toBe(1);

});
