import React from 'react';
import { shallow } from 'enzyme';
import MealCombo from '../component/MealCombo';
import sinon from 'sinon';

let wrapper;

beforeAll(() => {
    wrapper = shallow(<MealCombo />);
});

afterEach(() => {

    sinon.restore();

  });

test('it renders', () => {

    expect(wrapper.length).toBe(1);

});

test('Combo One select button calls handleSubmit', () => {

    const stubber = sinon.stub(MealCombo.prototype, 'handleSubmit').returns(true);
    let wrapper2 = shallow(<MealCombo handleSubmit={stubber}/>);

     expect(wrapper2.length).toBe(1);

     //Assert button is rendered
     const comboOneButtonSelect = wrapper2.find("[data-test='combo-one-button-select']");
     expect(comboOneButtonSelect.length).toBe(1);

     //Assert clicking the button updates the state
     expect(wrapper2.state().hiddenElement).toBeNull();
     expect(wrapper2.state().downloadCSV).toBe("");
     const event = {target: {id: "1"}};
     comboOneButtonSelect.simulate('click', event);
     expect(stubber.calledOnce).toEqual(true);
     
});

test('Combo two select button calls handleSubmit', () => {

    const stubber = sinon.stub(MealCombo.prototype, 'handleSubmit').returns(true);
    let wrapper3 = shallow(<MealCombo handleSubmit={stubber}/>);

     expect(wrapper3.length).toBe(1);

     //Assert button is rendered
     const comboTwoButtonSelect = wrapper3.find("[data-test='combo-two-button-select']");
     expect(comboTwoButtonSelect.length).toBe(1);

     //Assert clicking the button updates the state
     expect(wrapper3.state().hiddenElement).toBeNull();
     expect(wrapper3.state().downloadCSV).toBe("");
     const event = {target: {id: "2"}};
     comboTwoButtonSelect.simulate('click', event);
     expect(stubber.calledOnce).toEqual(true);

});

test('Combo three select button calls handleSubmit', () => {

    const stubber = sinon.stub(MealCombo.prototype, 'handleSubmit').returns(true);
    let wrapper4 = shallow(<MealCombo handleSubmit={stubber}/>);

     expect(wrapper4.length).toBe(1);

     //Assert button is rendered
     const comboThreeButtonSelect = wrapper4.find("[data-test='combo-three-button-select']");
     expect(comboThreeButtonSelect.length).toBe(1);

     //Assert clicking the button updates the state
     expect(wrapper4.state().hiddenElement).toBeNull();
     expect(wrapper4.state().downloadCSV).toBe("");
     const event = {target: {id: "2"}};
     comboThreeButtonSelect.simulate('click', event);
     expect(stubber.calledOnce).toEqual(true);

});