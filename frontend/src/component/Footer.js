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
                        <ListGroupItem tag="a" href="#">About Us</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Help</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Terms & Privacy</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Contact</ListGroupItem>
                    </ListGroup>
                    </Col>
                    <Col>
                    <ListGroup flush>
                    <ListGroupItem disabled tag="a" href="#">Eating</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Overview</ListGroupItem>
                        <ListGroupItem tag="a" href="#">How it Works</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Yuma for Business</ListGroupItem>
                    </ListGroup>
                    </Col>
                    <Col>
                    <ListGroup flush>
                    <ListGroupItem disabled tag="a" href="#">Catering</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Get Started</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Requirements</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Resources</ListGroupItem>
                    </ListGroup>
                    </Col>
                    <Col>
                    <ListGroup flush>
                    <ListGroupItem disabled tag="a" href="#">Connect</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Facebook</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Instagram</ListGroupItem>
                        <ListGroupItem tag="a" href="#">Twitter</ListGroupItem>
                    </ListGroup>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default Footer;