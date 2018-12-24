import React, { Component } from 'react';

class Retriever extends Component {

    constructor(apiPath) {
        super()
        
        fetch(apiPath)
        .then(function(response) {
        return response.json();
        })
        .then(function(myJson) {
        console.log(JSON.stringify(myJson));
        });
    }

    toString(){
        console.log("Inside the Retriever class, a call to toString");
        return "This is the toString function";
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

