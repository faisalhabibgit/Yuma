import React, {Component} from 'react';
import ApiToken from "../middleware/ApiToken";

import {
  Card, CardHeader,  CardBody,
  CardDeck, Button
} from 'reactstrap';
import DisplayUsers from "./DisplayUsers";
import CustomLogging from '../CustomLogging';

class Users extends Component {


  constructor(props) {
    super(props);

    this.checkAuthenticated();
  }

  componentDidMount() {
    window.scrollTo(0, 0);
  }



  checkAuthenticated(){

    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      CustomLogging.error('Check Authentification Dashboard: FAIL','dashboard');
      this.props.history.push(`/Login`)
    } else {
      CustomLogging.info('Check Authentification Dashboard: PASS','Dashboard');
    }

  }

  render() {
    return (
      
      <CardDeck data-test="users-card" style={{padding:'12px', height:'620px'}}>
          <Card body outline color="primary">
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <div>
                <Button variant="secondary"  onClick={this.handleModalChange3}>Update User</Button>
              </div>
            </CardHeader>
            <CardBody>
              <DisplayUsers />
            </CardBody>
          </Card>

        </CardDeck>

    );

  }
}


export default Users;
