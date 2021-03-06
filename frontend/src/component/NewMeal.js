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
import CustomLogging from '../CustomLogging';




const REDIRECTHOME = '/';
class NewMeal extends Component {


  constructor(props) {
    super(props);

    this.checkAuthenticated();
    this.state = {
      name: '',
      description: '',
      ingredients: [{ name: "", weight: "", calories: "", price: "", quantifier: "" }],
      nuts: 'false',
      dairy: 'false',
      gluten: 'false',
      shellfish: 'false',
      soy: 'false',
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
      CustomLogging.error('Check Authentification NewMeal: FAIL','NewMeal');
      this.props.history.push(`/Login`)
    } else {
      CustomLogging.info('Check Authentification NewMeal: PASS','NewMeal');
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

    if (["name", "weight", "calories", "price", "quantifier"].includes(event.target.className)) {

      let ingredients = [...this.state.ingredients];
      ingredients[event.target.dataset.id][event.target.className] = event.target.value;
      this.setState({ ingredients: ingredients }, () => CustomLogging.info(this.state.ingredients));

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
      ingredients: [...prevState.ingredients, { name: "", weight: "", calories: "", price: "", quantifier:"" }],
    }));
    CustomLogging.info('Ingredient is added!',"New Meal");
};

calculateCalories(e, idx){
    e.preventDefault();
    var index = idx;
    CustomLogging.info('calory calculated' )
    var ingr = this.state.ingredients[index]['name'];
    var weight = this.state.ingredients[index]['weight'];
    var quantifier = this.state.ingredients[index]['quantifier']
    var ingredientTempList = this.state.ingredients
    var calculatedIngredient = this.state.ingredients[index]

    var api = "https://api.edamam.com/api/nutrition-data?app_id=6fd2547b&app_key=61888ddf81b29e52ad9aaf1a8d5b4400&ingr="+ ingr +"%20" + weight + "%20" + quantifier;

    fetch(api)
      .then((response) => {
        return response.json();
      })
      .then((json) => {
        if (json['totalWeight'] === "0" && json['calories'] === "0"){
           alert('Please verify the ingredient name and/or the input weight.')}
        else{
            calculatedIngredient['calories'] = json['calories'];
            calculatedIngredient['weight'] = json['totalWeight'];
            calculatedIngredient['quantifier'] = "g";
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
    CustomLogging.info('Ingredient is removed!');
  };


  postMeal(array) {
    const mealBuilder = new BuildMeal();

    var ingredientArr = [];

    for (let i = 0; i < this.state.ingredients.length; i++) {
      var anIngredient = new Ingredients();
      CustomLogging.info("ingredient: " + i);

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
      <div style={{background: '#ADB7BF'}} >
      <Container>

        <Col sm="12" md={{ size: 12}}>
          <CardHeader  className="text-center" style={{background: '#B9C5D5', borderRadius: 10}}>
            <h2>Enter a New Meal</h2>
          </CardHeader>
          <br />

          <Form className="form" onSubmit={this.handleSubmit}>
            <Col >
              <FormGroup>
              <CardDeck data-test="name" style={{padding:'12px', height:'200px', borderRadius: 10}}>
                <Card  data-test="name-card">
                  <CardHeader  className="text-center" style={{background: '#B9C5D5'}}>
                    <h5 style={{color: 'black'}}> Name</h5>
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

                <Card  data-test="meal-description-card">
                  <CardHeader data-test="meal-description" className="text-center" style={{background: '#B9C5D5'}}>
                    <h5 style={{color: 'black'}}> Meal Description</h5>
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
                <br /><br />

                <Card  data-test="ingredient-card" style= {{background:'#D0DCE5', borderRadius: 10, borderColor:'#274F6C'}}>
                <CardBody className="text-center">
                {
                  this.state.ingredients.map((val, idx) => {
                    let ingredientId = `name-${idx}`, weightId = `weight-${idx}`, quantifierId = `quantifier-${idx}`, caloriesId = `calories-${idx}`, priceId = `price-${idx}`;
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
                          data-test='ingredient-name'
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
                          data-test='ingredient-weight'
                        />
                        <input
                          type="text"
                          name={quantifierId}
                          data-id={idx}
                          id={quantifierId}
                          value={this.state.ingredients[idx].quantifier}
                          onChange={this.handleChange}
                          className="quantifier"
                          placeholder="unit"
                        />
                        <br />
                        <label htmlFor={caloriesId}>Calories</label>
                        <input
                          style={{ marginLeft: 61}}
                          type="text"
                          name={caloriesId}
                          data-id={idx}
                          id={caloriesId}
                          value={this.state.ingredients[idx].calories}
                          onChange={this.handleChange }
                          className="calories"
                          data-test='ingredient-calories'
                        />
                        <br />
                        <br />
                        <br />
                        <div className="text-left"   style={{ marginLeft: 352 }}>
                        <label>Possible Food Allergies</label>
                        </div>


                      <CardBody className="text-left" style={{paddingLeft:'500px'}}>
                      <label>
                      <Input type="checkbox" id="nuts" onChange={this.handleChange} />
                        Tree Nuts
                      </label>

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
                      </CardBody>

                        <div>
                         <Button variant="secondary"  onClick={(e) => this.calculateCalories(e, idx)}>Calculate</Button>
                       </div>
                        <br />
                        <label htmlFor={priceId}>Price</label>
                        <input
                          style={{ marginLeft: 83 }}
                          type="text"
                          name={priceId}
                          data-id={idx}
                          id={priceId}
                          value={this.state.ingredients[idx].price}
                          onChange={this.handleChange}
                          className="price"
                          data-test='ingredient-price'
                        />
                        <br />
                        <br />
                        <div>
                         <Button variant="secondary"  data-test="delete-ingredient-button" onClick={(e) => { this.removeIngredient(e, idx) }}> Remove </Button>
                       </div>
                       <br />

                      </div>

                    )
                  })
                }
                <div className="text-left">
                 <Button variant="secondary" data-test="add-ingredient-button" onClick={(e) => {this.addIngredient(e)}}>Add new ingredient</Button>
                </div>
                </CardBody>
                </Card>
                <br />

            </FormGroup>
            <br />

              <div class="text-center" >

              <Button   variant="secondary" type="submit" value="Submit" size="lg" block>Submit</Button>



              </div>
            </Col>
          </Form>
        </Col>
      </Container>
      </div>
    );
  }
}

export default NewMeal;
