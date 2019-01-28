class CustomLogging {
  constructor(title) {
    this.title = {
      body: title ||  "---",
       };

    this.body = {
       };
  }

  setTitleStyle({ color, size }) {
    
      this.title.color = color;
      this.title.size = size;
  }

  setBodyStyle({ color, size }) {
    this.body.color = color;
    this.body.size = size;
  }   

  log(body = "") {
    console.log(
      `%c${this.title.body} | %c${body}`,
      `color: ${this.title.color}; font-weight: normal; font-size: "1rem";`,
      `color: ${this.body.color}; font-weight: normal; font-size: "1rem";`
    );
  }

}

export default CustomLogging;
