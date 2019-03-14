import React from 'react';
import { shallow } from 'enzyme';
import NewMeal from '../component/NewMeal';
import sinon from 'sinon';

let wrapper;

beforeEach(() => {
    
    const stubber = sinon.stub(NewMeal.prototype, 'checkAuthenticated').returns(true);
    wrapper = shallow(<NewMeal checkAuthenticated={stubber}/>);

  });

afterEach(() => {

    sinon.restore();

  });

test('New Meal Page renders', () => {
    
    expect(wrapper.length).toBe(1);
    
});

test('New Meal page renders "Enter name" input', () => {
    
    expect(wrapper.length).toBe(1);

    const nameInput = wrapper.find("[data-test='enter-meal-name']");
    expect(nameInput.length).toBe(1);

});

test('New meal page renders "meal description" input', () => {

    expect(wrapper.length).toBe(1);

    const descriptionInput = wrapper.find("[data-test='enter-meal-description']");
    expect(descriptionInput.length).toBe(1);

});

test('New meal page renders "add new ingredient" button', () => {


    expect(wrapper.length).toBe(1);

    const addIngredientButton = wrapper.find("[data-test='add-ingredient-button']");
    expect(addIngredientButton.length).toBe(1);
});

test('New meal page renders a single ingredient form upon INITIAL rendering', () => {

    expect(wrapper.length).toBe(1);

});