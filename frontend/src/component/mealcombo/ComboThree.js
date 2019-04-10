import React, { Component } from 'react';
import Retriever from '../../middleware/Retriever';
import DisplayCombo from './DisplayCombo';
import Loading from '../Loading';
import {
    Container
} from 'reactstrap';
import CustomLogging from "../../CustomLogging";


class ComboThree extends Component {
    constructor(props) {
        CustomLogging.info('inside combo 3','ComboThree');
        super(props);
        this.state = {
            apiObject: []
        }
    }

    componentDidMount() {
        CustomLogging.info('retrieving combo three','ComboThree');
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
            CustomLogging.alert("no combination report 3 to display","ComboThree");
            return <Loading />
        } else {
            CustomLogging.info("displaying combination report 3","ComboThree");
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
