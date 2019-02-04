import React, { Component } from 'react';
import { Spinner } from 'reactstrap';


class Loading extends Component {

    render() {
        return (
            <div>
                <p>loading...</p>
                <Spinner color="primary" />
            </div>
        );

    }
}

export default Loading;
