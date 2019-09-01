#!/bin/bash

cd moviecruiserserverapplication
source ./env-variable.sh
mvn clean package
cd ..
cd movieCruiserAuthenticationService
source ./env-variable.sh
mvn clean package
cd ..
