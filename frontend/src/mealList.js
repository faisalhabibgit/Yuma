import React, { Component } from 'react';
import logo from './yuma.svg';
import './App.css';
import './caterer.js';

class MealList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      caterer = this.prop.caterer,
      menu: null,
      supplies:null,
    };
  }

  render() {
    const caterer = this.propss.caterer;
    const MENU = [
      {id: 1, price: 7, foodGroup: [A,B,C,D], rating: 5, availability: true, reviews: ,},
      {id: 2, price: 6, foodGroup: [A. D], rating: 4, availability: false, reviews: ,},
      {id: 3, price: 7, foodGroup: [A,B,C], rating: 5, availability: false, reviews: ,},
      {id: 4, price: 5, foodGroup: [B,C,D], rating: 3, availability: true, reviews: ,},
      {id: 5, price: 10, foodGroup: [A,C,D], rating: 5, availability: true, reviews: ,},
      {id: 6, price: 12.44, foodGroup: [A,B,D], rating: 4, availability: true, reviews: ,}
    ];

    const listItems = MENU.map((d) => <li key={d.id}>{d}</li>);

    return (
      <div>
      {this.props.data}
      {listItems}
      </div>
    );
  }
};
