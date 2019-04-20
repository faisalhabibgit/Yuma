import React, { Component } from 'react';
import { Link } from "react-router-dom";

export default class DisplayUserByCompany extends Component{

  render(){
    return(
      <tbody>
      <tr key={this.props.userId}>
        <td>{this.props.lastName} </td>
        <td>{this.props.firstName} </td>
        <td><button><Link to="/theUserPage">Check User</Link></button></td>
      </tr>
    </tbody>
    )
  }
}
