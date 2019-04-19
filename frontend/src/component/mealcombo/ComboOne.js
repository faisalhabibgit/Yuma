import React, { Component } from 'react';
import Retriever from '../../middleware/Retriever';
import DisplayCombo from './DisplayCombo';
import Loading from '../Loading';
import {
    Container
} from 'reactstrap';
import CustomLogging from '../../CustomLogging';


class ComboOne extends Component {
    constructor(props) {
        CustomLogging.info('inside combo 1','ComboOne');
        super(props);
        this.state = {
            apiObject: []
        }
    }

    componentDidMount() {
        CustomLogging.info('retrieving combo 1','ComboOne');
        const retriever = new Retriever('api/combinationreport/weeklycombo');
        retriever.getEntityPromise()
            .then((obj) => {
                this.setState({
                     apiObject: obj[0],
                    });  // Note: Index = 0 for Combo 1
            })
    }

    getDisplay(){
        if (this.state.apiObject === undefined || this.state.apiObject.length === 0) {
            CustomLogging.alert("no combination report 1 to display","ComboOne");
            return <Loading />
        } else {
            CustomLogging.info("displaying combination report 1","ComboOne");
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

export default ComboOne;
