#!/bin/bash

GOOGLE_PROJECT_ID=jurer-playground

gcloud auth configure-docker europe-west1-docker.pkg.dev --project $GOOGLE_PROJECT_ID
./gradlew clean jib --info

if [ $? -ne 0 ]; then
  echo "Failed to produce image with Jib"
  exit 1
fi

image=$(cat ./build/image-name.txt)
gcloud run deploy makeit-quarkus-demo \
  --project $GOOGLE_PROJECT_ID \
  --image $image \
  --platform managed \
  --region europe-west1 \
  --allow-unauthenticated \
  --min-instances 1