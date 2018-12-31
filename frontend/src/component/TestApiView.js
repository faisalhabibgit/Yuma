import React, { Component } from 'react';

import ApiToken from '../middleware/ApiToken';

class TestApiView extends Component {

    render() {

        const apiToken = new ApiToken();
        apiToken.getToken('http://localhost:2020/api/auth/signin', 
        {email: 'whatsup1@email.com',
        password: 'idk1'})
        .then(data => console.log(JSON.stringify(data))) // JSON-string from `response.json()` call
        .catch(error => console.error("Inside TestApiView: " + error));

        return (
            <div>
                <h1>Test Api</h1>
            </div>
        );
    }
}

export default TestApiView;