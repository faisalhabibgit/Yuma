import React from 'react';
import { shallow } from 'enzyme';
import ProteinTypesChart from '../component/ProteinTypesChart';
import sinon from 'sinon';

let wrapper;

beforeAll(() => {
  //stub the componentDidMount method, as it causes a react error
  const cmDidMount = sinon.stub(ProteinTypesChart.prototype, 'componentDidMount').returns(true);
  wrapper = shallow(<ProteinTypesChart componentDidMount={cmDidMount}/>);

});

afterEach(() => {

  sinon.restore();

});

test('Protein Types Chart renders pie chart', () => {

  expect(wrapper.length).toBe(1);

  const pieChart = wrapper.find("[data-test='chart-protein']");
  expect(pieChart.length).toBe(1);

});
