class User{
  constructor(userId, firstName, lastName, email, plan, isActive, timestamp, password, ) {

  this.userId = userId;
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
  this.plan = plan;
  this.isActive = isActive;
  this.timestamp = timestamp;
  this.password = password;
  
  
  this.dislikesList = dislikesList;
    }
    
    toString(){
     return (this.userId + this.firstName + this.lastName + 
             this.email +this.password + this.isActive + this.plan + this.dislikesList);
  }
}

export default User;

 
