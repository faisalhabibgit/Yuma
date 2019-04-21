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

    postMessage() {

        var api_key = '227b11f7fe20a3bbeb5be013ba857407-3fb021d1-b66a254f';
        var DOMAIN = 'sandbox87c15faf9e284ce1888109640268cde8.mailgun.org';
        // var mailgun = require('mailgun-js')({ apiKey: api_key, domain: DOMAIN });

        var data = {
            from: 'Mailgun Sandbox <postmaster@sandbox87c15faf9e284ce1888109640268cde8.mailgun.org>',
            to: 'Jo Blo <yuma_admin@yuma.com>',
            subject: 'Feedback from ' + this.state.email,
            text: this.rating + '\n' + this.content
        };

        // mailgun.messages().send(data, function (error, body) {
        //     console.log(body);
        // });
        // You can see a record of this email in your logs: https://app.mailgun.com/app/logs

        // You can send up to 300 emails/day from this sandbox server.
        // Next, you should add your own domain so you can send 10,000 emails/month for free.


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