Travis: [![Build Status](https://travis-ci.com/faisalhabibgit/Yuma.svg?token=Mv3RFmyPQwmuAjmUYuib&branch=master)](https://travis-ci.com/faisalhabibgit/Yuma)

# Yuma

Yuma is a trusted personal caterer who can prepare your weekly meals for you, or your enterprise. Find meals that meet your diet and budget to benefit from having your weekly meals on hand.

-----

This is a multi-module Spring Boot React Apache Maven starter app with good defaults. The react app is built using [create-react-app](https://github.com/facebookincubator/create-react-app).

This project provides productive setup for building Spring Boot React applications. The application is divided into two Maven modules:

1. `backend`: This contains Java code of the application.
2. `frontend`: This contains all react JavaScript code of the application.

## Running the full application

You can build the package as a single artifact by running the `./mvnw clean install` in UNIX, or mvn clean install in Windows.
Next, you can run the application by executing:

```bash
$ java -jar backend/target/backend-0.1.0-SNAPSHOT.jar
```

*Make sure to use Java 1.8 with Maven.*

>*For MacOS*
```bash
$ export JAVA_HOME=`/usr/libexec/java_home -d 64 -v "1.8*"`
```

The application will be accessible at `http://localhost:2020`.

## Features

This starter comes bundled with the following features:

1. Multi module Maven project: A multi module project to modularize backend and frontend code separately.
2. Maven wrapper: So, you don't need to install Maven on your machine.
3. Checkstyle: Enforce sane coding standard guidelines.
4. ErrorProne: Find errors in your code(Not supported in JDK 9).
5. Copy paste detection: Uses PMD CPD check to detect code duplication.
6. CORS enabled: A global configuration is added to enable CORS so that frontend can work seamlessly with backend during development.
7. REST API base path: Sets the base REST API path to `/api`. You can configure it by changing `rest.api.base.path` property.
8. Bundle Git commit information

## Running the backend for development mode

There are multiple ways to run the backend. For development, you can use your favorite IDE and run the
`Application`. As soon as your code compiles, Spring Boot DevTools will reload the code.

You can also run the application using Maven.

```bash
$ cd backend
$  ../mvnw spring-boot:run
```
This launches the application on port 9000.

## Running the frontend for development mode

**You will need 6.0+ and yarn to run the dev server and build the project**.

Make sure to install [yarn on your development machine](https://yarnpkg.com/en/docs/install).

To install all the required binaries for your project, you can run following command.

```
$ cd frontend
$ ../mvnw frontend:install-node-and-yarn frontend:yarn
```
This launches the application on port 3000.

###Front-end dependencies
```
$ npm i reactstrap
````
For testing, we use Enzyme and Jest. By default, Jest is included with create-react-app. For Enzyme:

`yarn add enzyme enzyme-adapter-react-16 react-test-renderer`

Enzyme docs: https://facebook.github.io/create-react-app/docs/running-tests


Once the above command finishes, you can start the frontend using the `yarn start` command.

## Hot reloading

Both the frontend and backend support hot reloading.

## Running the tests

To run frontend tests, change directory to the frontend folder and execute `yarn test`. This launches the test watch mode.

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use _____ for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Faisal Habib** 

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc...
