#!/bin/sh

. ./image-env.sh

podman tag ${BASE_IMAGE} $REGISTRY/$REGISTRY_USER_ID/${BASE_IMAGE}

podman push $REGISTRY/$REGISTRY_USER_ID/${BASE_IMAGE}