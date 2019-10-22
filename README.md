# Deploy View Backend

Java application based on Hexagonal Architecture that retrieve data from Elasticsearch and exposes a REST endpoint so
a Angular frontend application can consume and display the data.


Build the package
```
mvn clean package
```

To build a Docker image
```
mvn build:docker
```

To run directly
```
java -jar target\deploy-view-0.0.1-SNAPSHOT.jar
```

To run with Docker
```
docker run -d  -p 8080:8080 
```

