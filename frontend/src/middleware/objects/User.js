class User {
  constructor() {
    console.log('Creating user...');
    this.userId = null;
    this.firstName = null;
    this.lastName = null;
    this.company = null;
    this.email = null;
    this.plan = null;
    this.isActive = null;
    this.timestamp = null;

    this.mealList = null;
    this.dislikesList = null;
  }

  setUserId(userId) { this.userId = userId }
  setFirstName(firstName) { this.firstName = firstName }
  setLastName(lastName) { this.lastName = lastName }
  setCompany(company) { this.company = company }
  setEmail(email) { this.email = email }
  setPlan(plan) { this.plan = plan }
  setIsActive(isActive) { this.isActive = isActive }
  setTimestamp(timestamp) { this.timestamp = timestamp }
  setMealList(mealList){this.mealList = mealList;}
  setDislikesList(dislikesList){this.dislikesList = dislikesList}

  toString() {
    return (this.userId +', '+ this.firstName +', '+ this.lastName +', '+ 
      this.email +', '+ this.password +', '+ this.isActive +', '+ this.plan +', '+ this.dislikesList);
  }
}

export default User;


