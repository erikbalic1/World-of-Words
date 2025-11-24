# worldofwords

## Overview
`worldofwords` is a small Spring Boot web application that provides a simple word game around categories such as animals, cities, countries and names (based on the hungarian "Ország-Város"(Country-City) game). The project is implemented in Java with Spring Boot and uses Maven for build and dependency management.

## The game
The game challenges players to supply words that belong to selected categories (Animals, Cities, Countries, Boy and Girl names). The server-side components (`model`, `service` and `repository`) hold category lists and a `GameController` exposes game interactions via the web UI or API.
The `gameReading.Main` class provides the possibility to play the game from terminal.

## Prerequisites
- Java 17+ (or Java 11 if your environment requires it)(21 user for development)
- Maven 3.6+
- IntelliJ IDEA (project tested in IntelliJ IDEA 2024.3.7)
- (Optional) Git

---

## Run locally

### Using IntelliJ IDEA 
*and optionally Maven from terminal*

1. Open the project in IntelliJ IDEA. *Navigate with terminal to `worldofwords` directory*
2. Run the main spring class `hu.unideb.inf.worldofwords.WorldofwordsApplication` from IDE *or from terminal, using `mvn spring-boot:run`* 
3. (Optional)Check one of the API endpoints: `http://localhost:8082/testCountries`, `http://localhost:8082/testCities`, `http://localhost:8082/testBoyNames`, `http://localhost:8082/testGirlNames`, `http://localhost:8082/testAnimals`, `http://localhost:8082/testCountry?country=...`, `http://localhost:8082/testCity?city=...`, `http://localhost:8082/testGirlName?girlName=...`, `http://localhost:8082/testBoyName?boyName=...`, `http://localhost:8082/testAnimal?animal=...`.
4. Run the `gameReading.Main` class *from terminal* or from IDE; this is the CLI facade of the game. It will connect to the API, and represent a CLI playable version of the game.

---

## Configuration
Application-level properties can be adjusted in:
- `src/main/resources/application.properties`

## Notes
- The web endpoints and UI are served by the controllers in `src/main/java/hu/unideb/inf/worldofwords/web`.
- If the application port or context path is customized, consult `application.properties`.