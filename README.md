# Infinity Games Factory

## Overview
To help you make a better decision about whether to buy the game you are interested in, the details of all the current most popular video games with various categories are gathered here. It only takes you a few minutes to look through the game information you want and figures out if meets your needs!

### Project Technical Overview
This application with SpringMVC design pattern is developed in Spring Framework by using Spring Boot, Hibernate, Flyway, Spring RESTful web services, Postman, Maven, Git, PostgresSql, Docker, JWT, Amazon SQS, and Amazon S3
* Business Rules
    1. Objects: Company, Console, Game, User, Role
    2. Relationships
        1. One company can have several consoles
        2. One console can have various games
        3. One user can have multiple roles
        4. One role can contain many users
        5. Only admin role have permission to promote or demote roles of users
    3. Permission for each role
          1. allowed_resource: what resources the role allowed to access
          2. allowed_create: whether or not could create resources
          3. allowed_delete: whether or not could delete resources
          4. allowed_update: whether or not could change resources
          5. allowed_read: whether or not could read resources
          6. allow Admin role to operate and access all resources 
* Structure
    1. root
        1. mvc: model, view, controller
        2. consumer: listening and handling messages 
* Development Approaches
    1. Create User, Role, Company, Console, and Game
    2. Pull and run PostgresSQL images to start container by using Docker
    3. Migrate database schema by Flyway
    4. Apply Hibernate to do object-relational mapping to model
    5. Implement application layer (persistence layer, business service and controller)    
    6. Verify the logic of methods by unit test and test Restful APIs by using Postman
    7. Use AWS S3 (Simple Storage Service) as storage service and implement Java Message Service to handle messages with AWS SQS (Simple Queue Service)
    8. After listening and handling messages from AWS SQS (Simple Queue Service), sending confirmation message to the client by using Twilio
    9. Transform local database to AWS cloud and deploy the project on AWS by CI/CD.
    
## Configure local environment
### Setup local database with docker
Refer to postgres docker [image](https://hub.docker.com/_/postgres) for environment option
```
docker run --name projectDB -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```
### Clone and switch to the repository
```
git clone https://github.com/Ran56/InfinityGamesFactory.git
cd InfinityGamesFactory/
```
### Environment properties configuration
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
```
### Migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/migrations), find all [migration schema](mvc/src/main/resources/db/migration)
```
mvn clean compile flyway:migrate -Ddatabase.url=${DB_URL} -Ddatabase.port=${DB_PORT} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD} -Ddatabase.name=${DB_NAME}
```
### Testing
```
mvn clean compile test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=${DB_URL} -Ddatabase.port=${DB_PORT} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD} -Dlogging.level.com.infinity=DEBUG -Dsecret.key=Aa123456 -Ddatabase.name=${DB_NAME} -Dspring.profiles.active=unit -DqueueName=${QUEUE} -q
```
### Create ```war``` package file in ```target``` directory
```
mvn clean compile package -DskipTests=true
```
### Deploy ```war``` file to [Apache Tomcat](http://tomcat.apache.org/), and run it
```
sh startup.sh
```
## API Guide

### User sign up

```
POST - http://localhost:8080/auth/
```

Request Body
```
{
    "name":"Testing Name",
    "password":"112233",
    "secretKey": null,
    "firstName": "Test",
    "lastName": "Name",
    "email":"testing@postman"
}
```
Response Body
```
{
    "id": 19,
    "name": "Testing Name",
    "password": "d0970714757783e6cf17b26fb8e2298f",
    "secretKey": null,
    "firstName": "Test",
    "lastName": "Name",
    "email": "testing@postman",
    "roles": [
        {
            "id": 3,
            "name": "user",
            "allowedResource": "/consoles,/consol,/games,/gams",
            "allowedRead": true,
            "allowedCreate": false,
            "allowedUpdate": false,
            "allowedDelete": false
        }
    ]
}
```

Sign Up Screenshot at Postman
![Sign up](READMEScreenshot/Signup.png)

### User Login to get JWT (Json Web Token)
```
POST - http://localhost:8080/auth
```
Request Body
```
{
    "name":"Testing Name",
    "password":"112233"
}
```
Response Body
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxOSIsInN1YiI6IlRlc3RpbmcgTmFtZSIsImlhdCI6MTU5ODgxNzQ0MiwiaXNzIjoiY29tLmluZmluaXR5IiwiZXhwIjoxNTk4OTAzODQyLCJhbGxvd2VkUmVhZFJlc291cmNlcyI6Ii9jb25zb2xlcywvY29uc29sLC9nYW1lcywvZ2FtcyIsImFsbG93ZWREZWxldGVSZXNvdXJjZXMiOiIiLCJhbGxvd2VkQ3JlYXRlUmVzb3VyY2VzIjoiIiwiYWxsb3dlZFVwZGF0ZVJlc291cmNlcyI6IiJ9.e1qvUZqxewnb7jYwe4-Ulcwp0CxEgP8TuQgdJb2xBVo"
}
```

Login Screenshot at Postman
![Login](READMEScreenshot/Login.png)

### Upload files to AWS S3
After giving the user permission to upload and download files, Upload files with user information and token to AWS S3 (Simple Storage Service), FileService will automatically generate an UUID (Universally Unique Identifier) attached to the end of the file original name to make it better to distinguish files in AWS S3
```
POST - http://localhost:8080/files/fileInfo
```
Response Body
```
fileInfo-27f280fb-aeea-4a24-ade6-e385804cffce.txt
```
Upload files Screenshot at Postman
![upload](READMEScreenshot/fileInfo.png)

### Get files from AWS S3
Get files with user information and token, only input a original file name, or a name-uuid FileService created
```
GET - http://localhost:8080/files/fileInfo?uuidOrOriginalName=fileInfo.txt
```
Response Body
```
"https://infinity-s3-bucket-1.s3.amazonaws.com/fileInfo-27f280fb-aeea-4a24-ade6-e385804cffce.txt?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20200831T031015Z&X-Amz-SignedHeaders=host&X-Amz-Expires=71999&X-Amz-Credential=AKIAVTI4FL2J5ALRMVEO%2F20200831%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=07798d1e885505ac5e428964aea0538c6ea6cf3b18baf2152d67e74008f285a8"
```
Get files with original name Screenshot at Postman
![getByOriginalName](READMEScreenshot/getByOriginalName.png)

```
GET - http://localhost:8080/files/fileInfo?uuidOrOriginalName=fileInfo-27f280fb-aeea-4a24-ade6-e385804cffce.txt
```
Response Body
```
"https://infinity-s3-bucket-1.s3.amazonaws.com/fileInfo-27f280fb-aeea-4a24-ade6-e385804cffce.txt?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20200831T032423Z&X-Amz-SignedHeaders=host&X-Amz-Expires=71999&X-Amz-Credential=AKIAVTI4FL2J5ALRMVEO%2F20200831%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=3b21795f0f335454c04b4a4f81f15bca57ffe31d177bf5acc65e07d6afe1ceae"
```
Get files with name-uuid Screenshot at Postman
![getByname-uuid](READMEScreenshot/getByname-uuid.png)

## CI/CD (Continuous Integration / Continuous Delivery)
What steps you should finish before you work with DevOps engineer
   1. Push source code to GitHub repository
   2. Ensure all unit tests pass successfully in specify docker container
   3. create ```war```  package file in specify docker container
   4. Build Docker ```image``` with ```war``` file, setenv.sh and Dockerfile
   5. Build and run container from ```image``` successfully
   
### Push source code to GitHub repository
Make sure the source code on the GitHub is the latest or runnable version all the time  

### Execute all unit tests in docker container
#### Pull ```Maven``` image and run container for testing by using ```Docker```
```
docker pull maven:3.6.0-jdk-8
docker run -it maven:3.6.0-jdk-8 /bin/bash
```
#### Pull complete project from GitHub
```
git clone ${REPOSITORY_URL}
```

#### Migrate schema by using ```Flyway```
```
mvn clean compile flyway:migrate -Ddatabase.url=${DB_URL} -Ddatabase.port=${DB_PORT} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD} -Ddatabase.name=${DB_NAME}
```
#### Find IP address of database container
Since running the project in the container instead of local environment, the IP address of ```PostgreSQL``` container is required to map port
```
docker inspect ${database_container_id} | grep "IPAddress"
```
#### Execute Unit tests in the container
```
mvn clean compile test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=${DB_URL} -Ddatabase.port=${DB_PORT} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD} -Dlogging.level.com.infinity=DEBUG -Dsecret.key=Aa123456 -Ddatabase.name=${DB_NAME} -Dspring.profiles.active=unit -DqueueName=${QUEUE} -q
```
### create ```war```  package file in docker container
```
mvn clean compile package -DskipTests=true
```
### Build ```image```
Put ```war``` file, setenv.sh file and Dockerfile together to build ```image```

```
docker build -t ${image_name}:${tag} .
```
### Build and run container from ```image``` 
```
docker run -e ENV=VALUE ${image_name}:${tag}
```

## About
* [GitHub](https://github.com/Ran56)

* [LinkedIn](https://www.linkedin.com/in/ran-zhang-16baab1a0/)