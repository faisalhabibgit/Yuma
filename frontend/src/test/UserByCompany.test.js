import React from 'react';
import { shallow } from 'enzyme';
import UsersByCompany from '../component/UsersByCompany';
import sinon from 'sinon';

let wrapper;

beforeAll(() => {
  //stub the componentDidMount method, as it causes a react error
  const cmDidMount = sinon.stub(UsersByCompany.prototype, 'componentDidMount').returns(true);
  wrapper = shallow(<UsersByCompany componentDidMount={cmDidMount}/>);

});

afterEach(() => {

  sinon.restore();
  
});

test('Users By company renders pie chart', () => {

  expect(wrapper.length).toBe(1);

  const pieChart = wrapper.find("[data-test='chart-users']");
  expect(pieChart.length).toBe(1);

});
