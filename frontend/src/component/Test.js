import React, { Component } from 'react';

import Retriever from '../middleware/Retriever'

class Test extends Component {

  constructor() {
    super();

    this.state = {
      apiObject: null,
    }

  }

  componentDidMount() {
    const retriever = new Retriever('api/meals');
    retriever.getEntityPromise()
      .then((obj) => {
        this.setState({ apiObject: obj });
      });
  }

  render() {
    console.log(this.state.apiObject);

    return (
      <p>Hello</p>
    );
  }
}

export default Test;