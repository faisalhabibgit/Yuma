import React, { Component } from 'react';
import Retriever from '../../middleware/Retriever';
import DisplayCombo from './DisplayCombo';
import Loading from '../Loading';
import {
    Container
} from 'reactstrap';
import CustomLogging from "../../CustomLogging";


class ComboTwo extends Component {
    constructor(props) {
        CustomLogging.info('inside combo 2','ComboTwo');
        super(props);
        this.state = {
            apiObject: []
        }
    }

    componentDidMount() {
        CustomLogging.info('retrieving combo two','ComboTwo');
        const retriever = new Retriever('api/combinationreport/weeklycombo');
        retriever.getEntityPromise()
            .then((obj) => {
                this.setState({
                     apiObject: obj[1],
                    });  // Note: Index = 1 for Combo 2
            })
    }

    getDisplay(){
        if (this.state.apiObject === undefined || this.state.apiObject.length === 0) {
            CustomLogging.alert("no combination report 2 to display","ComboTwo");
            return <Loading />
        } else {
            CustomLogging.info("displaying combination report 2","ComboTwo");
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
