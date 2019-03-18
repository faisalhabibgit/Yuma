import React, { Component } from 'react';


class Test extends Component {

  constructor(props) {
    super(props);

    this.state = {
      userInput: "",
      list: []
    }
  }

  render() {
    return (
      <div className="to-do-main-list">
        <input value={this.state.userInput} type="text"/>
      </div>
    );
  }

}
export default CompanyName;
