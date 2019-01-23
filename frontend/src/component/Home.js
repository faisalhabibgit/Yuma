import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import {
    Container, Row, Col,
    Card, CardImg, CardText, CardBody,
    CardTitle, Button
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';

class Home extends Component {
    constructor(props) {
        super(props);

        const apiToken = new ApiToken();
        if(!apiToken.isAuthenticated()){
          console.log('User Not Logged');
          this.props.history.push(`/Login`)
        }else{
          console.log('User Login Success');
        }

    }

    render() {
        return (
            <Container>
                <Row>
                    <Col>
                        <Card>
                            <CardImg top width="100%" src="https://resources.stuff.co.nz/content/dam/images/1/f/a/z/4/j/image.related.StuffLandscapeSixteenByNine.320x180.1fawv3.png/1478554384578.jpg" alt="Card image cap" />
                            <CardBody>
                                <CardTitle>Meals</CardTitle>
                                <CardText>Check the meal inventory</CardText>
                                <Link to="/Test"><Button>Search Now</Button></Link>
                            </CardBody>
                        </Card>
                    </Col>
                    <Col>
                        <Card>
                            <CardImg top width="100%" src="https://www.ingredientsnetwork.com/47/product/99/15/56/p991556th_S.jpg" alt="Card image cap" />
                            <CardBody>
                                <CardTitle>Add Meals</CardTitle>
                                <CardText>Add a new recipe</CardText>
                                <Link to="/NewMeal"><Button>Add Now</Button></Link>
                            </CardBody>
                        </Card>
                    </Col>
                     <Col>
                        <Card>
                            <CardImg top width="100%" src="https://davidhoganwriter.files.wordpress.com/2018/02/greek-food.jpg?w=520&h=350&crop=1" alt="Card image cap" />
                            <CardBody>
                                <CardTitle>Meal History</CardTitle>
                                <CardText>Find the dates</CardText>
                                <Link to="/NewMeal"><Button>Find Now</Button></Link>
                            </CardBody>
                        </Card>
                    </Col>
                    <Col>
                        <Card>
                            <CardImg top width="100%" src="https://i.imgur.com/QF3pQEZ.jpg" alt="Card image cap" />
                            <CardBody>
                                <CardTitle>Dashboard</CardTitle>
                                <CardText>Check the stats</CardText>
                                <Link to="/Dashboard"><Button>View Now</Button></Link>
                            </CardBody>
                        </Card>
                    </Col>
                    <Col>
                        <Card>
                            <CardImg top width="100%" src="https://i.imgur.com/RNMo8zj.png" alt="Card image cap" />
                            <CardBody>
                                <CardTitle>Settings</CardTitle>
                                <CardText>Edit and Configure</CardText>
                                <Link to="/NewMeal"><Button>Config</Button></Link>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        );

    }

}


export default Home;
