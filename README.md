# Kafka Streams API Examples

This project demonstrates the usage of Kafka Streams API through various examples, providing insights into stream
processing with Apache Kafka. It is built using Maven for dependency management and compilation, and further packaged
into a Docker image for easy deployment in containerized environments. The project is designed to be scalable and can be
integrated into distributed systems, offering a practical solution for processing real-time data streams efficiently.

---

## Requirements

* Docker 27.2.0
* Make 3.81
* Java 21
* Maven 3.9.9
* [Kafka 3.8.0](https://github.com/EightAugusto/development-infrastructure-provider)

---

## Run

```bash
make docker.start
```

---

## Stop

```bash
make docker.stop
```
