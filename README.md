# Automated Software Engineering — Home Automation

Coursework submission for an Automated Software Engineering module. The project
implements an agile-tariff home automation solution in two parts:

- **Part 1 — Data-Intensive System:** two Micronaut 4 microservices (an Internet of Things
  service and a Reactive Computation service) orchestrated with Docker Compose alongside
  Kafka and MariaDB.
- **Part 2 — Model-Driven Engineering:** the *Reactive Component Language* (RCL), a
  domain-specific language for describing the reactive components and Kafka topics of the
  scenario, with an Emfatic/Ecore metamodel, EVL validation, an Eclipse Sirius graphical
  editor, and an EGX/EGL code generator.

## Implemented Scenario: Agile-Tariff Battery Control

The chosen scenario controls a home battery against a 30-minute agile electricity tariff. It is
realised as a pipeline of four reactive components in the Reactive Computation microservice,
communicating only through Kafka topics and the IoT microservice's HTTP API.

The decision logic charges the battery when the import rate is cheap, discharges it to the grid
when the export rate is high, and otherwise only exports excess (`sell_excess`).

## Part 1 — Microservices

### IoT Microservice (IoTM)

Manages the home's rooms, sensors and actuators, persisting them in its own MariaDB
database (schema via Flyway).

Sensor readings are produced from inside the service: `HeatingSimulation` is a scheduled
method that simulates temperature readings, saving them to the database and publishing them
as Kafka records on `sensor-readings`.

### Reactive Computation Microservice (RCM)

Runs the battery-control pipeline. It consumes and produces Kafka records, persists the
latest value of every topic slot it observes to its own MariaDB database, registers each
component on startup via `@PostConstruct`, and controls IoTM actuators through IoTM's
generated OpenAPI client.

The two services are independently buildable: RCM talks to IoTM (and to the Agile Rates API)
only through OpenAPI-generated clients (`reactive/src/main/openapi`), with no Gradle
dependency between projects.

### Technology Stack

Java 17 · Micronaut 4.6.1 · Micronaut Data + Hibernate JPA · MariaDB 11 · Apache Kafka 3.9
(KRaft) · Flyway · Micronaut OpenAPI/Swagger · Docker + Docker Compose · Gradle · JUnit 5,
Mockito, Awaitility, and Micronaut Test Resources (Testcontainers).

### Building & Running

All Gradle commands are run per-project using each project's own wrapper. A running Docker
engine is required (Micronaut Test Resources starts MariaDB and Kafka in containers).

Run the unit tests for a microservice:

```shell
cd microservices/iot
./gradlew test
```

Build the production Docker image for a microservice (built-in Micronaut support):

```shell
cd microservices/iot
./gradlew dockerBuild
```

Start the whole system (after the images are built):

```shell
cd microservices
docker compose up --wait
```

Run the end-to-end tests across the orchestrated system (builds the images, starts the stack,
runs the tests, and tears it down):

```shell
cd microservices
./run-tests.sh
```

### Testing

Tests are split by scope. Each microservice has **unit tests** runnable with `./gradlew test`
(Micronaut Test Resources starts MariaDB and Kafka in containers). Both service modules apply the
JaCoCo plugin, so `./gradlew test` also writes a coverage report to
`build/reports/jacoco/test/html/index.html`:

- **IoTM** — controller tests drive each HTTP resource through the Micronaut HTTP client,
  covering both success and failure paths (400 on invalid input, 404 on unknown ids, 409 on
  duplicate names), plus pure-logic branch tests for the temperature simulator.
- **RCM** — pure-logic tests for the battery `decide(...)` function and the agile-rates JSON
  parsing, plus a context test asserting that all four components register on startup and that
  the inspection endpoints behave (including 404s).

The **end-to-end tests** (`end2end-tests`, run via `./run-tests.sh`) exercise the orchestrated
Compose stack: they confirm the seed data, that all four components register in RCM, and that a
battery decision propagates all the way through Kafka → RCM → IoTM's OpenAPI client to update a
battery actuator's target state. Runs are deterministic — the heating simulation is disabled and
the reactive schedules are pushed far out in the test profiles, so background timers do not race
the assertions.

### Service Ports & Swagger UI

When started via Docker Compose:

| Service | Port | Swagger UI |
| --- | --- | --- |
| IoTM (`iot`) | 8080 | http://localhost:8080/swagger-ui |
| RCM (`reactive`) | 8081 | http://localhost:8081/swagger-ui |
| Dummy rates (`rates`) | 8082 | — |

By default RCM reads live rates from `https://agilerates.uk`. To use the bundled dummy rates
service instead, switch the commented `micronaut.http.services.rates.*` lines in
`reactive/src/main/resources/application.properties` (Docker Compose already points RCM at the
`rates` container).

## Part 2 — Reactive Component Language (RCL)

RCL lets the home automation scenario be described declaratively as components and topics, and
then generates the repetitive Kafka plumbing for it. The projects under `language/` are Eclipse
plugin projects; open them in an Eclipse installation with EMF, Epsilon and Sirius.

### Metamodel

Defined in Emfatic at `uk.ac.york.cs.eng2.rcl/model/rcl.emf` (Emfatic generates the `.ecore`;
the `.genmodel` regenerates the model/edit/editor code). A `Model` owns `Component`s and
`Topic`s. A `Component` has one or more `Trigger`s (`TimeTrigger` with a `TimePeriod`, or
`TopicTrigger` referencing a `Topic`) and may `produce` topics. A `Topic` owns `Slot`s, each with
a name, a `SlotType` (integer, double, string, timestamp, long) and a `SlotPart` (body or key).

### Validation

`uk.ac.york.cs.eng2.rcl.evl/evl/rcl.evl` implements the constraints that the metamodel cannot
express on its own: unique component/topic names, unique slot names within a topic, valid
Java-identifier and Kafka-topic naming, every topic having a body slot, every component having
a trigger, every topic being involved with a component, valid time-period units/values, and
acyclicity of the component-topic graph (recursive cycle detection). These rules run automatically
during Sirius validation.

### Code Generation

`uk.ac.york.cs.eng2.rcl.generator` implements the following: for every topic, the EGX orchestrator
(`scripts/main.egx`) and EGL templates generate a Kafka producer, a topic factory, and a consumer,
under `uk.ac.york.eng2.reactive.generated`. The generated consumer persists each slot via
`TopicSlotService` and then triggers the components that depend on the topic.

Hand-written behaviour is preserved through **delegation** (rather than protected regions or
inheritance): the generated consumer calls `onTrigger(record)` on hand-written `*Activation`
beans in `...reactive.generated.activations`, so re-running the generator overwrites only the
generated producer/factory/consumer files and never touches manual code.
`uk.ac.york.cs.eng2.rcl.generator.dt` wires the generator into the Package Explorer's right-click
menu (right-click a `.rcl` file, choose a target folder, generate).

### Graphical Syntax & Model

`uk.ac.york.cs.eng2.rcl.viewpoint` provides the Eclipse Sirius graphical editor for RCL. A model
renders as a single diagram with light-blue **Component** nodes and light-orange **Topic** nodes,
connected by green `produces` edges and blue `triggers` edges, so data flow and control flow are
visually distinct.

The `model/` project contains `homeControl.rcl` — the battery scenario expressed in RCL,
mirroring the four components and topics implemented in Part 1 — and `representations.aird` with
its Sirius diagram.

## Configuration for Docker Engine 29+

If Docker Desktop is running normally and you see this error message while trying to run tests or start the application:

```
Caused by: java.lang.IllegalStateException: Could not find a valid Docker environment. Please see logs and check configuration
```

Then you may be affected by [this issue](https://github.com/micronaut-projects/micronaut-test-resources/issues/941).
Until it is fixed, you will need to manually tell Testcontainers which Docker API version to use.
From a Bash terminal (Linux/Mac console, or Git Bash on Windows), you will need to run:

```shell
echo api.version=1.44 > $HOME/.docker-java.properties
```

From PowerShell on Windows, you would instead run:

```shell
"api.version=1.44" | set-content $HOME/.docker-java.properties -Encoding Ascii
```

Note that the failure to start Docker may be cached by the Micronaut Test Resources Gradle plugin.
To reset this cache, run the `stopTestResourcesService` Gradle task.
