import React, { Component } from 'react';
import BuildMeal from './objectBuilder/BuildMeal';

class Retriever extends Component {

    constructor(apiPath) {
        super()

        switch(apiPath) {
            case 'api/meals':
            console.log('switch statement: api/meals');
                const x = new BuildMeal();
                break;
            case 'aaa':
                // code block
                break;
            default:
                // code block
          } 
        
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

