import React, { Component } from 'react';
import { Row, Col, ListGroup, ListGroupItem} from 'reactstrap';

class Footer extends Component {

    render() {
        return (
            <div className="footer">
                <hr/>
                <Row>
                    <Col>
                    <ListGroup flush body outline color="success">
                        <ListGroupItem disabled tag="a" href="#">Yuma</ListGroupItem>
                        <ListGroupItem disabled tag="a" href="#">© 2019 Yuma Foods Inc.</ListGroupItem>
                    </ListGroup>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default Footer;
