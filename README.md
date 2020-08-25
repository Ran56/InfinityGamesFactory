# Infinity Games Factory

## Overview
Are you still worried that the game you're interested in is so popular that it's out of stock? Are you still paying high prices for some popular games that are out of stock on the website? Here, you can always buy the game you want at full price and always stock it!

### Project Technical Overview
This application is developed in Spring Framework by using Spring Boot, Hibernate, Spring RESTful web services, Postman, Maven, PostgresSql, Docker, Amazon SQS, and Amazon S3.
* Business Rules
    1. zhang3
    1. li4
* Development Approaches
    1. zhang3
    1. li4
    
    
## Configure local environment
### setup local database with docker
Refer to postgres docker [image](https://hub.docker.com/_/postgres) for environment option.
```
docker run --name projectDB -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/migrations), find all [migration schema](src/main/resources/db/migration)
```
mvn clean compile flyway:migrate
```

```
mvn clean compile flyway:migrate -Dxxx
```

