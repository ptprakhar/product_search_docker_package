Tech Stack
===========

1. Java (Spring Boot)
2. Docker
3. Kafka
4. Logstash
5. ElasticSearch


My Approach (Architecture Explanation)
======================================

- I have used Elasticsearch for better and faster searching and it can be scaled in multiple clusters easily

- I have used kafka for queuing data so we can introduce multiple datasources easity in future, all sources have to push data to kafka topic. This give strong foundation to entier data import mechanism

- I have used logstash as “Consumer” it takes the data from Kafka TOPIC and pushes into the elastic Search (Simple 4-5 lines of configuration)

- Application is developed using Spring Boot Framework (java), that is also running in container


Running the application
========================
Entire application is running in docker containers

Docker Containers
	Zookeeper
	Kafka
	Kafdrop (Just for the visualization of kafka topic data)
	Elasticsearch
	Kibana (Just for the visualization of elasticsearch data)
	Logstash
	Spring boot app


Starting the application
========================

```bash
	 chmod +x deploy.sh
	./deploy.sh
```
or 

```bash
docker-compose up
```


Application URLs
========================

Spring Boot Application

Swagger 
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

Spring Boot APIs
http://localhost:8080/api/v1/dataImport/pushBulkData - POST
http://localhost:8080/api/v1/product/search?productName=Brown eggs&productCategory=dairy - GET

Kafka Kafdrop
localhost:9000

Elastic Search
localhost:9200

