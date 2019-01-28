class User {
  constructor() {

    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.plan = plan;
    this.isActive = isActive;
    this.timestamp = timestamp;


    this.dislikesList = dislikesList;
  }

  setUserId(userId) { this.userId = userId }
  setFirstName(firstName) { this.firstName = firstName }
  setLastName(lastName) { this.lastName = lastName }
  setEmail(email) { this.email = email }
  setPlan(plan) { this.plan = plan }
  setIsActive(isActive) { this.isActive = isActive }
  setTimestamp(timestamp) { this.timestamp = timestamp }

  toString() {
    return (this.userId + this.firstName + this.lastName +
      this.email + this.password + this.isActive + this.plan + this.dislikesList);
  }
}

export default User;


