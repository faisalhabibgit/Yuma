class Role{
  constructor(id, role){
    this.id = id;
    this.role =role;
  }

  toString() {
    return (this.id + this.role);
  }
}
export default Role;
