# events project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

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

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/events-0.0.1-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Provided examples

### RESTEasy JAX-RS example

REST is easy peasy with this Hello World RESTEasy resource.

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)


# Generate scaffold

```sh
export QUARKUS_VERSION="1.13.3.Final"

mvn io.quarkus:quarkus-maven-plugin:$QUARKUS_VERSION:create \
  -DprojectGroupId="com.redhat.gramola.events" \
  -DprojectArtifactId="events" \
  -DprojectVersion="0.0.1-SNAPSHOT" \
  -DclassName="EventsResource" \
  -Dpath="events"
```


# Project startup

```shell script
. ./image-env.sh
PROJECT_NAME=${PROJECT_ID}-${ARTIFACT_ID}-dev
DB_HOST=events-database
oc new-project ${PROJECT_NAME}
oc new-app -e POSTGRESQL_USER=luke -e POSTGRESQL_PASSWORD=secret -e POSTGRESQL_DATABASE=EVENTS centos/postgresql-10-centos7 --name=${DB_HOST} -n ${PROJECT_NAME}
oc label deployment/${DB_HOST} app.openshift.io/runtime=postgresql --overwrite -n ${PROJECT_NAME} && \
oc label deployment/${DB_HOST} app.kubernetes.io/part-of=${APP_NAME} --overwrite -n ${PROJECT_NAME}
```

Extensions

```sh
./mvnw quarkus:add-extension -Dextension="quarkus-resteasy-jsonb, quarkus-jdbc-postgresql, io.quarkus:quarkus-jdbc-h2, quarkus-hibernate-orm-panache, quarkus-smallrye-openapi, smallrye-health, openshift"
```

DB Properties

```properties
#################################
## BEGIN: Data Base related properties
%prod.quarkus.datasource.jdbc.url = jdbc:postgresql://${DB_HOST}:5432/${DB_NAME}
%prod.quarkus.datasource.db-kind = postgresql
%prod.quarkus.datasource.username = ${DB_USER}
%prod.quarkus.datasource.password = ${DB_PASSWORD}
%prod.db.type = PostgreSQL

%dev.quarkus.datasource.jdbc.url = jdbc:h2:mem:myDB
%dev.quarkus.datasource.db-kind=h2
%dev.quarkus.datasource.username = username-default
%dev.db.type = H2

%test.quarkus.datasource.jdbc.url = jdbc:h2:mem:myDB
%test.quarkus.datasource.db-kind=h2
%test.quarkus.datasource.username = username-default
%test.db.type = H2

## drop and create the database at startup (use `update` to only update the schema)
%prod.quarkus.hibernate-orm.database.generation = create
quarkus.hibernate-orm.database.generation = drop-and-create
quarkus.hibernate-orm.sql-load-script = import.sql
## show sql statements in log
quarkus.hibernate-orm.log.sql = true

## END: Data Base related properties
#################################
```

Deploy

```sh
./mvnw clean package -Dquarkus.kubernetes.deploy=true -DskipTests
```

AS CLUSTER ADMIN
```sh
oc adm policy add-scc-to-user privileged -z default -n ${PROJECT_NAME} && \
oc adm policy add-scc-to-user anyuid -z default -n ${PROJECT_NAME}
```


