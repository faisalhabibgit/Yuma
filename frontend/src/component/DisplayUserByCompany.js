import React, { Component } from 'react';
import { Link } from "react-router-dom";

export default class DisplayUserByCompany extends Component{

displayUsers(){
  console.log("consumer component run displayconsumers")
    let consumerList = [];

    for(let i in this.props.consumers){
      console.log(i)
      console.log("consumer last name  "+ this.props.consumers)
      consumerList.push(
        <li key={i}>
          {this.props.lastName}
          {this.props.firstName}
          <button><Link to="/theUserPage">Check User</Link></button>
        </li>
      )
    }
    console.log(consumerList.length)
    return(<ul>{consumerList}the return</ul>);
  }

  render(){
    // return(
    //   <tbody>
    //   <tr key={this.props.userId}>
    //     <td>{this.props.lastName} </td>
    //     <td>{this.props.firstName} </td>
    //     <td><button><Link to="/theUserPage">Check User</Link></button></td>
    //   </tr>
    // </tbody>
    // )

    return(
      <tbody>
        {displayUsers}
      </tbody>
    )
  }
}
