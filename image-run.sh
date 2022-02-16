#!/bin/sh

. ./image-env.sh

podman run -i --rm -p 8080:8080 ${PROJECT_ID}-${ARTIFACT_ID}:${GIT_HASH}