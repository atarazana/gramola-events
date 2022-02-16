#!/bin/sh

. ./image-env.sh

./mvnw clean package -DskipTests

#docker build -f src/main/docker/Dockerfile.jvm -t ${PROJECT_ID}-${ARTIFACT_ID}:${GIT_HASH} .
podman build -f src/main/docker/Dockerfile.jvm -t ${PROJECT_ID}-${ARTIFACT_ID}:${GIT_HASH} .

