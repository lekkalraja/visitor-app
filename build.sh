#!/bin/bash
docker build -t visitor-app .
docker tag visitor-app:latest $1:latest
docker push $1:latest