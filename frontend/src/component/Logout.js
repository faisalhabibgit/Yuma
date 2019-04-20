import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'
import ApiToken from '../middleware/ApiToken';
import CustomLogging from '../CustomLogging';

class Logout extends Component {

    render() {
        const apiToken = new ApiToken();
        apiToken.clearCookie();
        CustomLogging.info('User Logout Success','Logout');
            return(
                <Redirect to='/' />
            );
     
    }
}
export default Logout;
