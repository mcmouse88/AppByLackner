#!/bin/bash

echo "$API_TOKEN" > ./local.properties
echo "$NEWS_TOKEN" >> ./local.properties
echo "$FB_MESSAGING_TOKEN" >> ./local.properties
echo "$FIREBASE_SERVICES" > ./notification/firebase_cloud_messaging/google-services.json
echo "$FIREBASE_PUSH_FCM" > ./notification/firebase-push-fcm/google-services.json