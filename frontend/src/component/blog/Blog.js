import React, { Component } from 'react';
import '../stylesheet/blogStyle.css'
import {
    Container, Form, FormGroup, Label, Input, Button
} from 'reactstrap';


class Blog extends Component {

    constructor(props) {
        super(props);

        this.state = {
            email: '',
            rating: '',
            content: '',
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);

    }

    handleSubmit(event) {
        event.preventDefault();
        console.log(this.state);
    }

    handleChange(event) {
        var value = event.target.value;

        switch (event.target.name) {
            case 'email':
                this.setState({ email: value })
                break;
            case 'rating':
                this.setState({ rating: value })
                break;
            case 'content':
                this.setState({ content: value })
                break;

            default:
            // code block
        }
    }

    render() {
        return (
            <Container>
                <h2 className="title">Blog - Meal Feedback</h2>
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="userEmail">Email</Label>
                        <Input type="email"
                            name="email"
                            value={this.state.email}
                            id="userEmail"
                            placeholder="some@email.com"
                            onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="ratingSelect">Rating</Label>
                        <Input type="select"
                            name="rating"
                            value={this.state.rating}
                            id="ratingSelect"
                            onChange={this.handleChange}>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="allergySelectMulti">Select Health Labels</Label>
                        <Input type="select"
                            name="selectHealthLabels"
                            value={this.state.selectHealthLabels}
                            id="allergySelectMulti"
                            onChange={this.handleChange}
                            multiple>
                            <option>VEGAN</option>
                            <option>VEGETARIAN</option>
                            <option>DAIRY FREE</option>
                            <option>GLUTEN FREE</option>
                            <option>EGG FREE</option>
                            <option>PEANUT FREE</option>
                            <option>TREE NUT FREE</option>
                            <option>SOY FREE</option>
                            <option>SHELLFISH FREE</option>
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="contentText">Text Area</Label>
                        <Input type="textarea"
                            name="content"
                            value={this.state.content}
                            id="contentText"
                            onChange={this.handleChange} />
                    </FormGroup>
                    <Button type="submit">Submit</Button>
                </Form>
            </Container>
        );
    }


}

export default Blog;