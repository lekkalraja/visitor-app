#!/bin/bash
docker build -t visitor-app .
docker tag microservice-1:latest $1:latest
docker push $1:latest