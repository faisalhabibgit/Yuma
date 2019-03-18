import React, { Component } from 'react';


class UserByCompanyName extends Component {

  constructor(props) {
    super(props);

    this.state = {
      userInput: "",
      list: []
    }
  }

  handleQueryChange(input){
    this.setState({
      userInput: input
    }, ()=>console.log(input))
  }

  render() {
    return (
      <div className="to-do-main-list">
        <input
          onChange = { (e)=>this.handleQueryChange(e.target.value)}
          value={this.state.userInput}
          type="text"
        />
      </div>
    );
  }

}
export default UserByCompanyName;
