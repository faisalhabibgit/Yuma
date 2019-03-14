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

    const mealCard = wrapper.find("[data-test='meal-card']");

    expect(mealCard).toBeDefined();
    sinon.restore();

});

test('Home page renders "add meals card"', () => {

    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);

    const mealCard = wrapper.find("[data-test='add-meal-card']");

    expect(mealCard).toBeDefined();
    sinon.restore();

});

test('Home page renders "Meal history" card', () => {

    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);

    const mealCard = wrapper.find("[data-test='meal-history-card']");

    expect(mealCard).toBeDefined();
    sinon.restore();

});

test('Home page renders "stats" card', () => {

    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);

    const mealCard = wrapper.find("[data-test='stats-card']");

    expect(mealCard).toBeDefined();
    sinon.restore();


});

test('Home page renders "stats" card', () => {

    const stubber = sinon.stub(Home.prototype, 'checkAuthenticated').returns(true);
    const wrapper = shallow(<Home checkAuthenticated={stubber}/>);

    const mealCard = wrapper.find("[data-test='settings-card']");

    expect(mealCard).toBeDefined();
    sinon.restore();


});
