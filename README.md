# Simple Vert.x REST CRUD API

This project implements a simple CRUD using Vert.x and JPA/Hibernate

## Building

To build project:

  ```
  mvn clean package
  ```
## Packaging

The application is packaged as a _fat jar_, using the 
[Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/).

## Running

Once packaged, just launch the _fat jar_ as follows:

```
java -jar target/my-first-app-1.0-SNAPSHOT-fat.jar
```
Then, open a browser to http://localhost:8080.
