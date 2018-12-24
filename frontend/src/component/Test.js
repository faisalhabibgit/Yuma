import React, { Component } from 'react';

import Retriever from '../middleware/Retriever'

class Test extends Component {

  render() {

    const x = new Retriever('api/meals');

    return (
      <div>
        <p>{x.toString()}</p>
      </div>
    );
  }
}

export default Test;