# ktor-sample

<img src="https://repository-images.githubusercontent.com/40136600/f3f5fd00-c59e-11e9-8284-cb297d193133" alt="Ktor" width="100" style="max-width:20%;">

ktor-sample is a demonstration project powered by [Ktor](http://ktor.io) framework.
It showcases the usage of:

* [Ktor](http://ktor.io)
* [Guice](https://github.com/google/guice)
* [Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [kotlin.test](https://kotlinlang.org/api/latest/kotlin.test/)

## Building

Building the project with [Gradle](https://gradle.org/):

```shell
./gradlew clean build
```

Running tests:

```shell
./gradlew test
```

Starting the server:

```shell
./gradlew run
```

The server is configured to the port 8080.

## API

This project contains a single endpoint for managing quotes:

### Index

```http request
GET quotes/
```

### Find

```http request
GET quotes/<id>
```

### Post

```http request
POST quotes/
Content-Type: application/json
{
    "content": "An overwhelmingly awesome quote",
    "author": "An awesome person"
}
```

### Put

```http request

PUT quotes/<id>
Content-Type: application/json
{
    "content": "An overwhelmingly awesome quote",
    "author": "An awesome person"
}
```

### Delete

```http request
DELETE quotes/<id>
```
