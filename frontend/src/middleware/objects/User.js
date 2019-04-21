class User {
  constructor() {
    console.log('Creating user...');
    this.userId = null;
    this.firstName = null;
    this.lastName = null;
    this.email = null;
    this.plan = null;
    this.isActive = null;
    this.timestamp = null;
    this.company = null;
    this.mealList = null;
    this.dislikesList = null;
    this.allergies = null;

    this.consumerComments = null;
  }

  setUserId(userId) { this.userId = userId }
  setFirstName(firstName) { this.firstName = firstName }
  setLastName(lastName) { this.lastName = lastName }
  setEmail(email) { this.email = email }
  setPlan(plan) { this.plan = plan }
  setIsActive(isActive) { this.isActive = isActive }
  setTimestamp(timestamp) { this.timestamp = timestamp }
  setCompany(company) { this.company = company }
  setMealList(mealList){this.mealList = mealList;}
  setDislikesList(dislikesList){this.dislikesList = dislikesList}
  setCompany(company){this.company = company}
  setAllergies(allergies){this.allergies = allergies}
  setConsumerComments(consumerComments){this.consumerComments = consumerComments}

  toString() {
    return (this.userId +', '+ this.firstName +', '+ this.lastName +', '+ 
      this.email +', '+ this.password +', '+ this.isActive +', '+ this.plan +', '+ this.dislikesList);
  }
}

export default User;


