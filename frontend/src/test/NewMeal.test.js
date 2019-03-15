import React from 'react';
import { shallow } from 'enzyme';
import NewMeal, { addIngredient } from '../component/NewMeal';
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

test('New meal page renders "add new ingredient" button and adds an ingredient when clicked', () => {

    expect(wrapper.length).toBe(1);

    //assert button exists
    const addIngredientButton = wrapper.find("[data-test='add-ingredient-button']");
    expect(addIngredientButton.length).toBe(1);

    //mock the event, simulate clicking the button and re-render
    const event = { preventDefault: () => {} };
    addIngredientButton.simulate('click',event);
    wrapper.update();

    //assert there are now 2 ingredient forms
    const ingredients = wrapper.find("[data-test='initial-ingredient']");
    expect(ingredients.length).toBe(2);


});

test('New meal page renders a single ingredient form upon INITIAL rendering', () => {

    expect(wrapper.length).toBe(1);

    const initialIngredient = wrapper.find("[data-test='initial-ingredient']");
    expect(initialIngredient.length).toBe(1);

});

test(' New meal page renders remove button for each ingredient, and removes the form when pressed', () =>{

    expect(wrapper.length).toBe(1);

    //assert button exists
    const addIngredientButton = wrapper.find("[data-test='add-ingredient-button']");
    expect(addIngredientButton.length).toBe(1);

    //mock the event, simulate clicking the button and re-render
    const event = { preventDefault: () => {} };
    addIngredientButton.simulate('click',event);
    wrapper.update();

    //assert there are now 2 ingredient forms
    const ingredients = wrapper.find("[data-test='initial-ingredient']");
    expect(ingredients.length).toBe(2);

    const deleteIngredientButton = wrapper.find("[data-test='delete-ingredient-button']");
    //console.log(deleteIngredientButton.at(0));

    //delete the first form
    const event2 = {preventDefault: () => {}};
    const event3 = {idx: {index: 0}};
    deleteIngredientButton.at(0).simulate('click', event2, event3);
    wrapper.update();

    //assert first is gone
    expect(ingredients.length).toBe(1);
    console.log(ingredients.debug());
    expect(deleteIngredientButton.at(0).key()).toBeNull();

    


});