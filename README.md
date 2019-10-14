# Credit Demo


### Prerequisites
There is no prerequisites. 

### Running
$ ./mvnw spring-boot:run

### Running Standalone
$ ./mvnw package && java -jar target/campaign-0.1.0-SNAPSHOT.jar

### Building Docker Image
$ ./mvnw install dockerfile:build

### Running Docker Container
$ docker run -p 8080:8080 -t democredit/credit

## Built With
* [Spring-Boot](https://projects.spring.io/spring-boot) - IOC Container
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors
Metin YAVUZ
