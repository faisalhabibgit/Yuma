import React, { Component } from 'react';

import Retriever from '../middleware/Retriever'

class Test extends Component {

  render() {

    const retriever = new Retriever('api/meals');
    console.log(retriever.getEntity());

    return (
      <div>
        <p>hi</p>
      </div>
    );
  }
}

export default Test;