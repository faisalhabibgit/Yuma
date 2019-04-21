import React, { Component } from 'react';
import {
  Container, Table, Button
} from 'reactstrap';
import Retriever from '../middleware/Retriever';
import CustomLogging from '../CustomLogging';

class DisplayUsers extends Component {

  constructor(props) {
    super(props);
    this.state = {
      apiObject: [],
      displayData: [],
      firstLetter : 'all',
      lastLetter : 'all',
    }
    
    this.onAlphabetClickFirst = this.onAlphabetClickFirst.bind(this);
    this.onAlphabetClickLast = this.onAlphabetClickLast.bind(this);
    this.handleUpdate = this.handleUpdate.bind(this);
  }

  componentDidMount() {
    const retriever = new Retriever('api/rest/all');
    CustomLogging.info('retrieving all users','DisplayUsers');
    retriever.getEntityPromise()
      .then((obj) => {
        var matchedArr = [];
        for (let index = 0; index < obj.length; index++) {
          const element = obj[index];
          matchedArr.push(element);
        }
        this.setState({ apiObject: matchedArr });
        this.setState({ displayData: matchedArr });
      })
  }

  prepareFirstLetterAlphabets ()  {
    let result = [];
    result.push(
      <button  onClick={this.onAlphabetClickFirst} id='all' >All </button>
    )
    for(let i=65; i<91; i++) {
      result.push(
        <button   onClick={this.onAlphabetClickFirst} id={String.fromCharCode(i)} >{String.fromCharCode(i)}</button>
      )
    }
    return result;
  }

  onAlphabetClickFirst(e){
    var firstLetter = e.target.id;
    this.setState({firstLetter:firstLetter});
    var lastLetter = this.state.lastLetter;
    var matchedArr;
    if (lastLetter=== 'all' && firstLetter==='all') {
      matchedArr = this.state.apiObject;
    }
    else if (firstLetter==='all'){

      matchedArr = this.state.apiObject.filter((element) => (element.lastName.charAt(0).toLowerCase()=== lastLetter.toLowerCase()))
    }
    else if (lastLetter==='all'){

      matchedArr = this.state.apiObject.filter((element) => (element.firstName.charAt(0).toLowerCase()=== firstLetter.toLowerCase()))
    }
    else  {
      matchedArr = this.state.apiObject.filter((element) => (element.firstName.charAt(0).toLowerCase() === firstLetter.toLowerCase() && element.lastName.charAt(0).toLowerCase() === lastLetter.toLowerCase()))
    }
    this.setState({displayData: matchedArr});
    CustomLogging.info('displaying matched users with first letter: ' + this.state.firstLetter + 'last letter: ' + this.state.lastLetter,'DisplayUsers');
  }

  prepareLastLetterAlphabets ()  {
    let result = [];
    result.push(
      <button  onClick={this.onAlphabetClickLast} id='all' >All </button>
    )
    for(let i=65; i<91; i++) {
      result.push(
        <button   onClick={this.onAlphabetClickLast} id={String.fromCharCode(i)} >{String.fromCharCode(i)}</button>
      )
    }
    return result;
  }

  handleUpdate() {
    
    CustomLogging.info("User Updated");
    const retriever = new Retriever('api/update/users');
    CustomLogging.info('updating users','DisplayUsers');
    retriever.getEntityPromise();
  }

  onAlphabetClickLast(e) {
    var lastLetter = e.target.id;
    this.setState({lastLetter: lastLetter});
    var matchedArr;
    var firstLetter = this.state.firstLetter;

    if (lastLetter=== 'all' && firstLetter==='all') {
      matchedArr = this.state.apiObject;
    }
    else if (firstLetter==='all'){

      matchedArr = this.state.apiObject.filter((element) => (element.lastName.charAt(0).toLowerCase()=== lastLetter.toLowerCase()))
    }
    else if (lastLetter==='all'){

      matchedArr = this.state.apiObject.filter((element) => (element.firstName.charAt(0).toLowerCase()=== firstLetter.toLowerCase()))
    }
    else  {
      matchedArr = this.state.apiObject.filter((element) => (element.firstName.charAt(0).toLowerCase() === firstLetter.toLowerCase() && element.lastName.charAt(0).toLowerCase() === lastLetter.toLowerCase()))
    }

    this.setState({displayData: matchedArr});
    CustomLogging.info('displaying matched users with first letter: ' + this.state.firstLetter + 'last letter: ' + this.state.lastLetter,'DisplayUsers');
  }

  render() {

    return(

      <Container>
        <div className='text-center' style={{padding:'12px'}}>
        
        <Button variant="secondary"  onClick={this.handleUpdate}>Update Users</Button>
      
          <h5> First Name beginning with:  <h7 style={{ color: '#599BE9' }}>{this.state.firstLetter.toLowerCase()} </h7> </h5>
          {this.prepareFirstLetterAlphabets()}

        </div>

        <div className='text-center' style={{padding:'12px'}}>
          <h5> Last Name beginning with:  <h7 style={{ color: '#599BE9' }}>{this.state.lastLetter.toLowerCase()} </h7> </h5>
          {this.prepareLastLetterAlphabets()}
        </div>

        <div className='text-center' style={{padding:'12px'}}> <h4> Number of Users:{' '+this.state.displayData.length} </h4> </div>
        <div style={{ padding:'12px', maxWidth:'1800px', maxHeight:'275px', overflow:'scroll'}}>
          <Table bordered condensed>
            <thead style={{background: '#599BE9',color:'white'}}>
            <th className="text-center" >First Name</th>
            <th className="text-center" >Last Name </th>
            <th className="text-center" >isActive</th>
            </thead>
            <tbody style={{background:'#d3d3d3'}}>

            {
              this.state.displayData.map(x =>
                <tr>
                  <td>{x.firstName.toString()}</td>
                  <td>{x.lastName.toString()}</td>
                  <td>{x.isActive.toString()}</td>
                </tr>
              )}
            </tbody>
          </Table>
        </div>
      </Container>
    );

  }
}

export default DisplayUsers;
