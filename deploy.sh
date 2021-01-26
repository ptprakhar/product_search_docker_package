#!/bin/bash

function trap_ctrlc() {
	echo "Shutting down docker."
	sudo docker-compose down
	echo "Stopped bash opeation."
	exit 2
}

trap "trap_ctrlc" 2

#Running
cd ./search &&
sudo mvn clean install -DskipTests &&
#again coming back and running docker-compose
cd .. &&
sudo docker-compose build &&
sudo docker-compose up