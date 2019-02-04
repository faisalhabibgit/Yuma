import React, { Component } from 'react';
import Retriever from '../../middleware/Retriever';
import DisplayCombo from './DisplayCombo';
import Loading from '../Loading';
import {
    Container
} from 'reactstrap';


class ComboTwo extends Component {
    constructor(props) {
        console.log('inside combo 2');
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
                     apiObject: obj[1],
                    });  // Note: Index = 1 for Combo 2
            })
    }

    getDisplay(){
        if (this.state.apiObject == undefined || this.state.apiObject.length == 0) {
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

export default ComboTwo;
