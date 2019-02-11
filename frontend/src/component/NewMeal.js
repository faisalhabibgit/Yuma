import React, { Component } from 'react';
import {
  Col, Form,
  FormGroup, Label, Input,
  Button,
  Container,
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import Ingredients from '../middleware/objects/Ingredients';
import Meal from '../middleware/objects/Meal';
import BuildMeal from '../middleware/objectBuilder/BuildMeal';

const REDIRECTHOME = '/';

class NewMeal extends Component {

  constructor(props) {
    super(props);

    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      console.log('User Not Logged');
      this.props.history.push(`/Login`)
    } else {
      console.log('User Login Success');
    }

    this.state = {
      name: '',
      description: '',
      ingredients: [{ name: "", weight: "", calories: "", price: "" }],
      nuts: 'false',
      dairy: 'false',
      gluten: 'false',
      shellfish: 'false',
      soy: 'false'
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.postMeal = this.postMeal.bind(this);
    this.setFlags = this.setFlags.bind(this);
  }

  handleSubmit(event) {

    event.preventDefault();

    if (this.state.name.length < 1) {
      alert('Please enter a name.');
    } else if (this.state.description.length < 1) {
      alert('Please enter a description');
    } else if (this.state.ingredients.length < 1) {
      alert('Please enter the ingredients');
    } else {

      let array = this.setFlags();
      this.postMeal(array);


      alert("Meal created!");
      //redirect to home
      this.props.history.push(REDIRECTHOME);

    }

  }

  handleChange(event) {

    if (["name", "weight", "calories", "price"].includes(event.target.className)) {

      let ingredients = [...this.state.ingredients];
      ingredients[event.target.dataset.id][event.target.className] = event.target.value;
      this.setState({ ingredients }, () => console.log(this.state.ingredients));

    } else {

      const target = event.target;
      const value = target.type === 'checkbox' ? target.checked : target.value;
      const id = target.id;

      this.setState({ [id]: value });
    }
  }

  addIngredient = (e) => {
    e.preventDefault();
    this.setState((prevState) => ({
      ingredients: [...prevState.ingredients, { name: "", weight: "", calories: "", price: "" }],
    }));
  };
  
  calculate = (e) =>{
    e.preventDefault();
    this.setState(() => ({
      ingredients: [{calories: this.calculateCalories() }],
    }))
    
  };

  //TODO
  // Take name of ingredient and return calories from edamam api call
  calculateCalories (){
    var countTrial = "400";
  return countTrial;
  };
  
  removeIngredient(e, index) {
    e.preventDefault();
    this.state.ingredients.splice(index, 1);
    this.setState({ ingredients: this.state.ingredients });
  };


  postMeal(array) {
    const mealBuilder = new BuildMeal();

    var ingredientArr = [];

    for (let i = 0; i < this.state.ingredients.length; i++) {
      var anIngredient = new Ingredients();

      anIngredient.setName(this.state.ingredients[i]['name']);
      anIngredient.setWeight(this.state.ingredients[i]['weight']);
      anIngredient.setCalories(this.state.ingredients[i]['calories']);
      anIngredient.setPrice(this.state.ingredients[i]['price']);

      ingredientArr.push(anIngredient);
    }

    var aMeal = new Meal();
    aMeal.setName(this.state.name);
    aMeal.setDescription(this.state.description);
    aMeal.setIsAvailable(true);
    aMeal.setFlags(array);
    aMeal.setIngredients(ingredientArr);

    mealBuilder.addMeal(aMeal);

  }

  setFlags() {

    let array = [];

    if (this.state.nuts === true) {
      array.push("nuts");
    }

    if (this.state.dairy === true) {
      array.push("dairy");
    }

    if (this.state.gluten === true) {
      array.push("gluten");
    }

    if (this.state.shellfish === true) {
      array.push("shellfish");
    }

    if (this.state.soy === true) {
      array.push("soy");
    }

    return array;
  }

  render() {
    return (
      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
          <h2>Enter a New Meal</h2>

          <br />

          <Form className="form" onSubmit={this.handleSubmit}>
            <Col >
              <FormGroup>
                <Label>Name</Label>
                <Input
                  type="text"
                  name="name"
                  id="name"
                  placeholder="Chicken Parmesan"
                  onChange={this.handleChange}
                />

                <br />

                <Label>Meal Description</Label>
                <Input
                  type="text"
                  name="description"
                  id="description"
                  placeholder="Chicken basted in tomato sauce."
                  onChange={this.handleChange}
                />

                <br />

                <Label>Ingredients</Label>
                <button style={{ marginLeft: 40 }} onClick={this.addIngredient}>Add new ingredient</button>
                <br /><br />
                {
                  this.state.ingredients.map((val, idx) => {
                    let ingredientId = `name-${idx}`, weightId = `weight-${idx}`, caloriesId = `calories-${idx}`, priceId = `price-${idx}`;
                    return (
                      <div key={idx}>
                        <br />
                        <label htmlFor={ingredientId}>{`Ingredient #${idx + 1}`}</label>
                        <input
                          style={{ marginLeft: 25 }}
                          type="text"
                          name={ingredientId}
                          data-id={idx}
                          id={ingredientId}
                          value={this.state.ingredients[idx].name}
                          onChange={this.handleChange}
                          className="name"
                        />
                        <br />
                        <label htmlFor={weightId}>Weight</label>
                        <input
                          style={{ marginLeft: 70 }}
                          type="text"
                          name={weightId}
                          data-id={idx}
                          id={weightId}
                          value={this.state.ingredients[idx].weight}
                          onChange={this.handleChange}
                          className="weight"
                        />
                        <br />
                        <label htmlFor={caloriesId}>Calories</label>
                        <input
                          style={{ marginLeft: 65 }}
                          type="text"
                          name={caloriesId}
                          data-id={idx}
                          id={caloriesId}
                          value={this.state.ingredients[idx].calories}
                          onChange={this.handleChange}
                          className="calories"
                        />
                        <button style={{ marginLeft: 40 }} onClick={this.calculate}>Calculate</button>
                        <br />
                        <label htmlFor={priceId}>Price</label>
                        <input
                          style={{ marginLeft: 86 }}
                          type="text"
                          name={priceId}
                          data-id={idx}
                          id={priceId}
                          value={this.state.ingredients[idx].price}
                          onChange={this.handleChange}
                          className="price"
                        />
                        <br />
                        <br />
                        <button onClick={(e) => { this.removeIngredient(e, idx) }}> Remove </button>
                      </div>
                    )
                  })
                }

                <br />

                <Label style={{ fontWeight: "bold" }}> Possible Food Allergies </Label>

                <FormGroup row>
                  <Col sm={{ size: 10 }}>
                    <FormGroup check>
                      <Label>
                        <Input type="checkbox" id="nuts" onChange={this.handleChange} />
                        Tree Nuts
                        </Label>
                      <br />
                      <Label>
                        <Input type="checkbox" id="dairy" onChange={this.handleChange} />
                        Dairy
                        </Label>
                      <br />
                      <Label>
                        <Input type="checkbox" id="gluten" onChange={this.handleChange} />
                        Gluten
                        </Label>
                      <br />
                      <Label>
                        <Input type="checkbox" id="shellfish" onChange={this.handleChange} />
                        Shellfish
                        </Label>
                      <br />
                      <Label>
                        <Input type="checkbox" id="soy" onChange={this.handleChange} />
                        Soy
                        </Label>
                    </FormGroup>
                  </Col>
                </FormGroup>

              </FormGroup>
            </Col>
            <Button type="submit" value="Submit">Submit</Button>
          </Form>
        </Col>

      </Container>
    );
  }
}

export default NewMeal;
