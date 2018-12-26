import React, { Component } from 'react';

import Retriever from '../middleware/Retriever'

class Test extends Component {

  render() {

    const retriever = new Retriever('api/meals');
    retriever.getEntityPromise()
      .then((obj) => {console.log(obj)})

    return (
      <div>
        <p>hi</p>
      </div>
    );
  }
}

export default Test;