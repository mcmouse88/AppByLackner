#!/bin/bash

{
  echo API_KEY="$API_TOKEN"
  echo NEWS_API_KEY="$NEWS_TOKEN"
  echo FB_SERVER_KEY="$FB_MESSAGING_TOKEN"
  echo STOCK_MARKET_API_KEY="$STOCK_MARKET_API_KEY"
} > ./local.properties
echo "$FIREBASE_SERVICES" > ./notification/firebase_cloud_messaging/google-services.json
