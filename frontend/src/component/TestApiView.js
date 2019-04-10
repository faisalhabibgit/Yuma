import React, { Component } from 'react';

import ApiToken from '../middleware/ApiToken';
import CustomLogging from "../CustomLogging";

class TestApiView extends Component {

    render() {

        const apiToken = new ApiToken();
        apiToken.getToken('http://localhost:2020/api/auth/signin', 
        {email: 'whatsup1@email.com',
        password: 'idk1'})
        .then(data => CustomLogging.info('Token from cookie: ' + apiToken.getCookie('yuma-token')));

        return (
            <div>
                <h1>Test Api</h1>
            </div>
        );
    }
}

export default TestApiView;