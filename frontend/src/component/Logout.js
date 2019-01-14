import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'
import ApiToken from '../middleware/ApiToken';

class Logout extends Component {

    render() {
        const apiToken = new ApiToken();
        apiToken.clearCookie();
            return(
                <Redirect to='/' />
            );
    }
}
export default Logout;
