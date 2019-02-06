import React, { Component } from 'react';
import Retriever from '../../middleware/Retriever';
import DisplayCombo from './DisplayCombo';
import Loading from '../Loading';
import {
    Container
} from 'reactstrap';


class ComboThree extends Component {
    constructor(props) {
        console.log('inside combo 3');
        super(props);
        this.state = {
            apiObject: []
        }
    }

    componentDidMount() {
        console.log('component mounted');
        const retriever = new Retriever('api/combinationreport/weeklycombo');
        retriever.getEntityPromise()
            .then((obj) => {
                this.setState({
                     apiObject: obj[2],
                    });  // Note: Index = 2 for Combo 3
            })
    }

    getDisplay(){
        if (this.state.apiObject === undefined || this.state.apiObject.length === 0) {
            console.log(this.state.apiObject);
            return <Loading />
        } else {
            return <DisplayCombo data={this.state.apiObject} />
        }
    }

    render() {
        return (
            <Container>
                { this.getDisplay() }
            </Container>
        );

    }
}

export default ComboThree;
