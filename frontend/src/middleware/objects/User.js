class User{
  constaructor(firstName, lastName, email, password){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this. password = password;
    }
    
    tostring(){    
    this.firstName + this.lastName + this.email +this.password;
  }
}

exports default User;
