import React, { Component } from 'react';

class Retriever extends Component {

    constructor() {
        super()
        
        fetch('api/meals')
        .then(function(response) {
        return response.json();
        })
        .then(function(myJson) {
        console.log(JSON.stringify(myJson));
        });
    }

    render() {
        
        return (
            <div>
                <h1>Check the console.</h1>
            </div>
        );
      }

}

export default Retriever;

