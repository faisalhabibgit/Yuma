class User{
  constructor( userId, firstName,  lastName,  email,  password,  isActive,  plan, dislikesList) {

  this.userId = userId;
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
  this.password = password;
  this.isActive = isActive;
  this.plan = plan;
  this.dislikesList = dislikesList;
    }
    
    toString(){
     return (this.userId + this.firstName + this.lastName + 
             this.email +this.password + this.isActive + this.plan + this.dislikesList);
  }
}

export default User;

 
