#!/bin/bash

echo API_KEY="$API_TOKEN" > ./local.properties
echo NEWS_API_KEY="$NEWS_TOKEN" >> ./local.properties
echo FB_SERVER_KEY="$FB_MESSAGING_TOKEN" >> ./local.properties
echo "$FIREBASE_SERVICES" > ./notification/firebase_cloud_messaging/google-services.json
