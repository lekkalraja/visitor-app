#!/bin/bash
ip=`kubectl cluster-info|grep "Kubernetes maste"|awk -F'https://' '{print$2}'|cut -d: -f1`
curl  http://$ip:31200/visitor