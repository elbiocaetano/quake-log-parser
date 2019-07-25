# Quake Log Parser
Parses a Quake III log file and expose the results in REST endpoints

## Getting Started
When the server is started, it reads and parse the file quake-log.log and save the parsed data to a H2 memory database. Then it expose the data in the following endpoints.

## Endpoints
### GET /v1/games
Return all games according to limit and offset

* offset = zero based page number (default 0)
* limit = number of records per page (default 10)

#### Response
  ```
   {
    "meta": {
      "limit": 10,
      "offset": 0,
      "recordCount": 10,
      "totalRecords": 21
    },
    "records": [
      {
        "id": 1,
        "players": [],
        "kills": {}
      },
      {
        "id": 2,
        "players": [
          "Isgalamido",
          "Mocinha"
        ],
        "kills": {
          "Mocinha": 0,
          "Isgalamido": 0
        }
      }]
      }
  ```
  In case of invalid offset
  ```
  {
  "developerMessage": "Game  not found",
  "userMessage": "Tried to find a game, but did not find any",
  "errorCode": 404
}
```

### GET /v1/games/[id]
* id - id of the game. Must be a number greater than or equal to 1

#### Response
```
{
  "meta": {
    "limit": 1,
    "offset": 0,
    "recordCount": 1,
    "totalRecords": 1
  },
  "records": [
    {
      "id": 3,
      "players": [
        "Mocinha",
        "Isgalamido",
        "Zeh",
        "Dono da Bola"
      ],
      "kills": {
        "Dono da Bola": 0,
        "Mocinha": 0,
        "Isgalamido": 1,
        "Zeh": 0
      }
    }
  ]
}
```

If a invalid character is sent, for example `/v1/games/a`
```
{
  "developerMessage": "Invalid value 'a' for parameter id. It must be a number",
  "userMessage": "Invalid value 'a' for parameter id. It must be a number",
  "errorCode": 400
}
```

If a invalid number is sent, for example `/v1/games/0`
```
{
  "developerMessage": "findById.id: id must be greater than or equal 1",
  "userMessage": "findById.id: id must be greater than or equal 1",
  "errorCode": 400
}
```

Follow the next steps in order to run this project on your local environment.

### Prerequisites

Install [Java JDK 11+](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).The JDK (Java Development Kit) is a development environment for building applications, applets, and components using the Java programming language.

To build the project ans install the dependencies you will need [Maven 3.6+](https://maven.apache.org/download.cgi).


### Installing

After installing the prerequisites, run the installation of the dependencies of the project. In the main folder of the project, run the following command

```
mvn clean install
```

```
mvn clean package
```
This will generate a target folder with a jar named `quake-log-parser-[version].jar`. For example: `quake-log-parser-2.0.0.jar`;

To start the server run the following command

```
java -jar quake-log-parser-[version].jar
```

The development server will start in the port 8080

## Running the tests

To run the test run the following command

```
mvn test
```

There are 3 test suits: controller, service and parser. The controller test the endpoint and input parameters validation. The service suit test the business logic and the communication with the data layer. The parser suit test the parser and its logic.

## Deployment

You can use the jar generated in the previous step to deploy in your application server

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Apache Maven](https://maven.apache.org/index.html) - Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.
* [H2 Database Engine](https://www.h2database.com/html/main.html) - Fast and open source database that can be used in embedded mode, or in server mode
## Authors

* **Elbio Caetano** - *Initial work* - [Elbio Caetano](https://github.com/elbiocaetano)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
