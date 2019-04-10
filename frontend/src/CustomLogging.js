class CustomLogging {
  constructor(title) {
    this.title = {
      body: title ||  "---",
    };

    this.body = {
    };

    this.component = {
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

  setComponentStyle({ color, size }) {
    this.component.color = color;
    this.component.size = size;
  }


  log(body = "",component="") {
    console.log(
      `%c${this.title.body} | %c${component} | %c${body} `,
      `color: ${this.title.color}; font-weight: normal; font-size: "1rem";`,
      `color: ${this.component.color}; font-weight: normal; font-size: "1rem";`,
      `color: ${this.body.color}; font-weight: normal; font-size: "1rem";`
    );
  }

  error(body = "",component="") {

    this.setBodyStyle({ color: 'red', size: '0.75rem' });
    this.log(body,component)

  }

  info(body = "",component="") {
    this.setBodyStyle({ color: 'green', size: '0.75rem' });
    this.log(body,component)

  }

  alert(body = "",component="") {
    this.setBodyStyle({ color: 'orange', size: '0.75rem' });
    this.log(body, component)

  }

}

export default new CustomLogging('log');
