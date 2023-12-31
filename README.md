# talk-quarkus-with-jnosql-lite

Project created during the presentation: [Esquenta TDC Future SouJava + Quarkus Club: Simplifying NoSQL Integration with Quarkus and JNoSQL](https://www.youtube.com/watch?v=PUm5zdrEkoo). Get the slides [here](https://docs.google.com/presentation/d/1wPn4R9dUvCKXuzhN23NNtcUJNMe0URdVQmP6BDmjI3Y/edit?usp=sharing): 

This project uses Quarkus, the Supersonic Subatomic Java Framework, and [Eclise JNoSQL Lite](https://github.com/eclipse/jnosql-extensions#jnosql-lite), persisting at [MongoDB database](https://www.mongodb.com).

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

### Startup MongoDB

**PS: It's needed to initialize the MongoDB before to run the application**

execute the `docker-compose.yml` file like below:

```bash
docker-compose up -d
```

Access the Mongo Express by this link: http://localhost:8081 with `admin` as user and `pass` as password.

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/jnosql-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)