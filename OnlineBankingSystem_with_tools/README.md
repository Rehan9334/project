# OnlineBankingSystem

This is a sample Spring Boot Online Banking System (learning/prototype).
It includes account management, transactions, JWT auth, MySQL integration, and REST APIs.

## Quick start

1. Update `src/main/resources/application.properties` with your MySQL credentials and a secure `jwt.secret`.
2. Build: `./mvnw clean package` (or `mvn clean package`).
3. Run: `./mvnw spring-boot:run` or `java -jar target/onlinebanking-0.0.1-SNAPSHOT.jar`.

## Notes on Maven Wrapper
This ZIP includes `mvnw` and `mvnw.cmd` scripts and the `.mvn/wrapper/maven-wrapper.properties` file.
To use the wrapper you need the `maven-wrapper.jar` under `.mvn/wrapper/` (not included here). If you want, I can add the full wrapper jar in a follow-up.
