# Infinity Games Factory by Ran Zhang

## Overview
To help you make a better decision about whether to buy the game you are interested in, the details of all the current most popular video games with various categories are gathered here. It only takes you a few minutes to get the game information you want and figures out if meets your needs!!

### Project Technical Overview
This application with SpringMVC design pattern is developed in Spring Framework by using Spring Boot, Hibernate, Flyway, Spring RESTful web services, Postman, Maven, Git, PostgresSql, Docker, JWT, Amazon SQS, and Amazon S3.
* Business Rules
    1. Objects: Company, Console, Game, User, Role
    2. Relationships:
        1. One company can have several consoles
        2. One console can have various games
        3. One user can have multiple roles
        4. One role can contain many users
* Structure
    1. root
        1. mvc: model, view, controller
        2. consumer: listening and handling messages 
* Development Approaches
    1. Create User, Role, Company, Console, and Game
    2. Pull and run PostgresSQL images to start container by using Docker
    3. Migrates database schema by Flyway
    4. Apply Hibernate to do object-relational mapping to model
    5. Implement application layer (persistence layer, business service and controller)    
    6. Verify the logic of methods by unit test and test Restful APIs by using Postman
    7. Use AWS S3 (Simple Storage Service) as storage service and implement Java Message Service to handle messages with AWS SQS (Simple Queue Service) 
    8. Transform local database to AWS cloud and deploy the project on AWS by CI/CD.
    
## Configure local environment
### 1.Setup local database with docker
Refer to postgres docker [image](https://hub.docker.com/_/postgres) for environment option.
```
docker run --name projectDB -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### 2.Clone and switch to the repository
```
git clone https://github.com/Ran56/InfinityGamesFactory.git
cd InfinityGamesFactory/
```
### 3.Environment properties configuration
```
-Dlogging.level.com.infinity=DEBUG
-Ddatabase.driver=org.postgresql.Driver
-Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect
-Ddatabase.url=${DB_URL}"
-Ddatabase.name=${DB_NAME}"
-Ddatabase.port=${DB_PORT}"
-Ddatabase.user=${DB_USER}"
-Ddatabase.password=${DB_PASSWORD}"
-Dsecret.key=AaBbCc123"
-DbucketName=${BUCKET}"
-Dspring.profiles.active=dev"
-DqueueName=${QUEUE}"
-Djms.queue.name=${QUEUE}"
```
### 4.Migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/migrations), find all [migration schema](src/main/resources/db/migration)
```
mvn clean compile flyway:migrate -Ddatabase.url=${DB_URL} -Ddatabase.port=${DB_PORT} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD} -Ddatabase.name=${DB_NAME}
```
### 5.Test
```
mvn clean compile test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=${DB_URL} -Ddatabase.port=${DB_PORT} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD} -Dlogging.level.com.infinity=DEBUG -Dsecret.key=Aa123456 -Ddatabase.name=${DB_NAME} -Dspring.profiles.active=unit -DqueueName=${QUEUE} -q
```
### 6.Create ```war``` package file in ```target``` directory
```
mvn clean compile package -DskipTests=true
```
### 7.Deploy ```war``` file to [Apache Tomcat](http://tomcat.apache.org/), and run it
```
sh startup.sh
```

