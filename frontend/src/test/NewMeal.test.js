import React from 'react';
import { shallow } from 'enzyme';
import NewMeal, { addIngredient } from '../component/NewMeal';
import sinon from 'sinon';


let wrapper;

beforeEach(() => {
    
    const stubber = sinon.stub(NewMeal.prototype, 'checkAuthenticated').returns(true);

  //stub the componentDidMount method, as it causes a react error
  const cmDidMount = sinon.stub(NewMeal.prototype, 'componentDidMount').returns(true);
  wrapper = shallow(<NewMeal checkAuthenticated={stubber} componentDidMount={cmDidMount}/>);

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

test('Renders Name Card', () => {

  const nameCard = wrapper.find("[data-test='name-card']");
  expect(nameCard.length).toBe(1);

});

test('Renders Description Card', () => {

  const descriptionCard = wrapper.find("[data-test='meal-description-card']");
  expect(descriptionCard.length).toBe(1);

});

test('Renders Ingredient Card', () => {

  const ingredientCard = wrapper.find("[data-test='ingredient-card']");
  expect(ingredientCard.length).toBe(1);

});

test('Renders Possible Allergies Card', () => {

  const allergiesCard = wrapper.find("[data-test='possible-allergies-card']");
  expect(allergiesCard.length).toBe(1);

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

    //Assert there are 2 delete ingredient buttons
    const deleteIngredientButton = wrapper.find("[data-test='delete-ingredient-button']");
    expect(deleteIngredientButton.length).toBe(2);

    //Assert that the state is updated once the remove button is pressed
    wrapper.instance().removeIngredient(event,0);
    expect(wrapper.state().ingredients.length).toBe(1);
    
    //Assert the ui now has only 1 ingredient form
    const ingredientsAfter = wrapper.find("[data-test='initial-ingredient']");
    expect(ingredientsAfter.length).toBe(1);
});


