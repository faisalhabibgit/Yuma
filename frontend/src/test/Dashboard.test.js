import React from 'react';
import { shallow } from 'enzyme';
import Dashboard from '../component/Dashboard';
import sinon from 'sinon';

let wrapper;

beforeEach(() => {

  //stub the authentication token
  const stubber = sinon.stub(Dashboard.prototype, 'checkAuthenticated').returns(true);

  //stub the componentDidMount method, as it causes a react error
  const cmDidMount = sinon.stub(Dashboard.prototype, 'componentDidMount').returns(true);
  wrapper = shallow(<Dashboard checkAuthenticated={stubber} componentDidMount={cmDidMount}/>);

  });

afterEach(() => {

    sinon.restore();

  });

test('Combo page renders', () => {

    expect(wrapper.length).toBe(1);

});

test('Renders meal combo card', () => {

    const mealComboCard = wrapper.find("[data-test='meal-combo-card']");
    expect(mealComboCard.length).toBe(1);

});

test('Renders available meals card', () => {

    const availableMealsCard = wrapper.find("[data-test='available-meal-card']");
    expect(availableMealsCard.length).toBe(1);

});

test('Renders all meals card', () => {

    const allMealsCard = wrapper.find("[data-test='all-meals-card']");
    expect(allMealsCard.length).toBe(1);

});

test('Renders Users Card', () => {

    const usersCard = wrapper.find("[data-test='users-card']");
    expect(usersCard.length).toBe(1);

});
