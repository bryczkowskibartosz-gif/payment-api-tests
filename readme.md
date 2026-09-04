# Payment API Tests

Simple REST API test automation project built with Java, JUnit 5, REST Assured and Maven.

## Tech stack

- Java 21+
- JUnit 5
- REST Assured
- Maven
- Hamcrest
- Git / GitHub

## Test scenarios

The project currently covers:

- GET resource by ID
- POST resource creation
- Query parameter filtering
- 404 response for a missing resource
- Multi-step API flow using data extracted from a previous response
- Validation that all returned resources match the requested user

## API

Tests use:

https://jsonplaceholder.typicode.com

JSONPlaceholder is a public fake REST API used for testing and prototyping.

## Running the tests

Make sure Java and Maven are installed.

Run:

```bash
mvn test

Expected result:

Tests run: 5
Failures: 0
Errors: 0
BUILD SUCCESS

Project structure
payment-api-tests/
├── pom.xml
├── README.md
└── src/
    └── test/
        └── java/
            └── tests/
                └── JsonPlaceholderTest.java