import React, { Component } from 'react';


class DisplayCombo extends Component {
    constructor(props) {
        super(props);

        this.state = {
            apiObject: this.props.data,
            displayLimit: 10,
        }
    }

    render() {
        return (
            <ul>
                <li>{ this.state.apiObject.numberOfBlanks.toString() }</li>
                <li>{ this.state.apiObject.combinationScore.toString() }</li>
                <li>{ this.state.apiObject.consumerList.toString() }</li>
                <li>{ this.state.apiObject.mealsList.toString() }</li>
            </ul>
        );

    }
}

export default DisplayCombo;
