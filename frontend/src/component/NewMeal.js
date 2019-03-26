import React, { Component } from 'react';
import {
  Col, Form,
  FormGroup, Label, Input,
  Button,
  Container,Card, CardHeader,  CardBody,
  CardDeck
} from 'reactstrap';
import ApiToken from '../middleware/ApiToken';
import Ingredients from '../middleware/objects/Ingredients';
import Meal from '../middleware/objects/Meal';
import BuildMeal from '../middleware/objectBuilder/BuildMeal';

const REDIRECTHOME = '/';

class NewMeal extends Component {


  constructor(props) {
    super(props);

    this.checkAuthenticated();

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
    // this.calculate = this.calculate.bind(this);
    this.calculateCalories = this.calculateCalories.bind(this);
    // this.caloriesFromWeight = this.caloriesFromWeight.bind(this);
    this.addIngredient = this.addIngredient.bind(this);

  }
  
  componentDidMount() {
    window.scrollTo(0, 0);
  }

  checkAuthenticated(){

    const apiToken = new ApiToken();
    if (!apiToken.isAuthenticated()) {
      console.log('User Not Logged');
      this.props.history.push(`/Login`)
    } else {
      console.log('User Login Success');
    }
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
      this.setState({ ingredients: ingredients }, () => console.log(this.state.ingredients));

    } else {

      const target = event.target;
      const value = target.type === 'checkbox' ? target.checked : target.value;
      const id = target.id;

      this.setState({ [id]: value });
    }
  }

addIngredient(e){
    e.preventDefault();
    this.setState((prevState) => ({
      ingredients: [...prevState.ingredients, { name: "", weight: "", calories: "", price: "" }],
    }));
  };

calculateCalories(e, idx){
    e.preventDefault();
    var index = idx;
    console.log("this" + this)
    console.log("calulateCalories(index), index = " + index )
    var ingr = this.state.ingredients[index]['name'];
    var weight = this.state.ingredients[index]['weight'];
    var ingredientTempList = this.state.ingredients
    var calculatedIngredient = this.state.ingredients[index]

    var api = "https://api.edamam.com/api/nutrition-data?app_id=6fd2547b&app_key=61888ddf81b29e52ad9aaf1a8d5b4400&ingr="+ ingr +"%20" + weight;

    fetch(api)
      .then((response) => {
        return response.json();
      })
      .then((json) => {
        if (json['totalWeight'] === "0" && json['calories'] === "0"){
           alert('Please verify the ingredient name and/or the input weight.')}
        else{
            calculatedIngredient['calories'] = json['calories'];
            ingredientTempList[idx] = calculatedIngredient;
            this.setState(() => ({
                ingredients: ingredientTempList,
            }));
      }})
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
      console.log("ingedient: " + i);

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
      <div style={{background: '#ADB7BF'}}>

      <Container>
        <Col sm="12" md={{ size: 6, offset: 3 }}>
        <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
          <h2>Enter a New Meal</h2>
        </CardHeader>
          <br />

          <Form className="form" onSubmit={this.handleSubmit}>
            <Col >
              <FormGroup>
          <CardDeck data-test="name" style={{padding:'12px', height:'450px'}}>
          <Card>
            <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black', fontFamily: 'Comic Sans MS'}}> Name</h5>
            </CardHeader>
            <CardBody>
            <Input
                  type="text"
                  name="name"
                  data-test="enter-meal-name"
                  id="name"
                  placeholder="Chicken Parmesan"
                  onChange={this.handleChange}
                />
            </CardBody>
          </Card>

          <Card>
            <CardHeader data-test="meal-description" className="text-center" style={{background: '#B9C5D5'}}>
              <h5 style={{color: 'black', fontFamily: 'Comic Sans MS'}}> Meal Description</h5>
            </CardHeader>
            <CardBody>
            <Input
                  class="form-control"
                  type="text"
                  name="description"
                  data-test="enter-meal-description"
                  id="description"
                  placeholder="Chicken basted in tomato sauce."
                  onChange={this.handleChange}
                />
            </CardBody>
          </Card>

        </CardDeck>

                       
                <br />
               
                <Label>Ingredients</Label>
                <br />
                <div>
                <Button variant="secondary"  data-test="add-ingredient-button" onClick={(e) => {this.addIngredient(e)}}>Add new ingredient</Button>
                </div>
                <br /><br />
                {
                  this.state.ingredients.map((val, idx) => {
                    let ingredientId = `name-${idx}`, weightId = `weight-${idx}`, caloriesId = `calories-${idx}`, priceId = `price-${idx}`;
                    return (
                      <div data-test="initial-ingredient" key={idx}>
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
                          onChange={this.handleChange }
                          className="calories"
                        />
                        <div>
                         <Button variant="secondary"  onClick={(e) => this.calculateCalories(e, idx)}>Calculate</Button>
                       </div>
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
                        <div>
                         <Button variant="secondary"  data-test="delete-ingredient-button" onClick={(e) => { this.removeIngredient(e, idx) }}> Remove </Button>
                       </div>
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

      </div>
    );
  }
}

export default NewMeal;
