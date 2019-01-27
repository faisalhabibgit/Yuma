import React, { Component } from 'react';
import {
    ListGroup, ListGroupItem
} from 'reactstrap';
import Retriever from '../middleware/Retriever';


class DisplayMeals extends Component {

    constructor(props) {
        super(props);
        this.state = {
            apiObject: [],
            displayLimit: 10,
        }
    }

    componentDidMount() {
        const retriever = new Retriever('api/meals');
        retriever.getEntityPromise()
          .then((obj) => {
            var matchedArr = [];

            for (let index = 0; index < this.state.displayLimit; index++) {
                const element = obj[index];
                matchedArr.push(element);
            }
            this.setState({ apiObject: matchedArr });
          })
    }

    render() {
        console.log(this.state.apiObject);
        return (
            <ListGroup>
                {
                    this.state.apiObject.map(x => <ListGroupItem>{x.toString()}</ListGroupItem>)
                }
            </ListGroup>
        );
    }
}

export default DisplayMeals;
