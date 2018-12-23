import React, { Component } from 'react';
import logo from './yuma.svg';
import './App.css';

class Caterer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      caterer:null,
    };
  }

  render() {
    //temporarily mocking
    const CATERER = [
      {menu: 13232, supplies: null, address: "244 crazy street, Montreal, Qc"},
      {menu: 1232, supplies: null, address: "1243 TechnoCar street, Montreal, Qc"},
      {menu: 878, supplies: null, address: "7678 Shennanigan street, Montreal, Qc"},
      {menu: 9562, supplies: null, address: "99363 Antony street, Montreal, Qc"}
    ];
    const listItems = MENU.map((d) => <li key={d.id}>{d}</li>);
    return (
      <button
        className="caterer"
        onClick={() => this.setState({caterer: APICALL FOR CATEREr })}
      >
        {this.state.value}
      </button>
    );
  }
}
