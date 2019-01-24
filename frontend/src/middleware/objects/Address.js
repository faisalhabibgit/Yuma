class Address {

  constructor(streetAddress, street, city, state, country, postalCode) {
    this.streetAddress = streetAddress;
    this.street = street;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postalCode = postalCode;
  }

  toString() {
    return this.streetAddress + this.street + this.city + this.state + this.country + this.postalCode ;
  }
}

export default Address;
