import React from 'react';
import { shallow } from 'enzyme';
import Home from '../component/Home';
import sinon from 'sinon';

test('Home page renders', () => {
    
    //stub the authentication token
    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);
    
    expect(wrapper.length).toBe(1);
    sinon.restore();
});

test('Home page renders meal card and button', () =>{

    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);

    //check if card renders
    const mealCard = wrapper.find("[data-test='meal-card']");
    expect(mealCard).toBeDefined();

    //check if button renders
    const mealButton = wrapper.find("[data-test='meal-button']");
    expect(mealButton.length).toBe(1);

    sinon.restore();
});

test('Home page renders "add meals card"', () => {

    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);

    const mealCard = wrapper.find("[data-test='add-meal-card']");
    expect(mealCard).toBeDefined();
    
    //check if button renders
    const addMealButton = wrapper.find("[data-test='add-meal-button']");
    expect(addMealButton.length).toBe(1);
    
    sinon.restore();

});

test('Home page renders "Meal history" card', () => {

    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);

    const mealCard = wrapper.find("[data-test='meal-history-card']");
    expect(mealCard).toBeDefined();
    
    //check if button renders
    const historyButton = wrapper.find("[data-test='history-button']");
    expect(historyButton.length).toBe(1);
    
    sinon.restore();

});

test('Home page renders "stats" card', () => {

    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);

    const mealCard = wrapper.find("[data-test='stats-card']");
    expect(mealCard).toBeDefined();
    
    //check if button renders
    const statsButton = wrapper.find("[data-test='stats-button']");
    expect(statsButton.length).toBe(1);
    
    sinon.restore();


});


