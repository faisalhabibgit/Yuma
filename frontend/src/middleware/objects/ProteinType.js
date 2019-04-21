class ProteinType {

  constructor() {
    this.BEEF = null;
    this.FISH = null;
    this.LAMB = null;
    this.POULTRY = null;
    // this.score = null;

  }

  setBeef(BEEF){this.BEEF = BEEF}
  setFish(FISH){this.FISH = FISH}
  setLamb(LAMB){this.LAMB = LAMB}
  setPoultry(POULTRY){this.POULTRY = POULTRY}
  // setScore(score){this.score = score}


  toString() {
    return (this.BEEF + this.FISH + this.LAMB + this.POULTRY);
  }
}
export default ProteinType;
