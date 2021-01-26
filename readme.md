Tech Stack
===========

1. Java (Spring Boot)
2. Docker
3. Kafka
4. Logstash
5. ElasticSearch


My Approach (Architecture Explanation)
======================================

- I have used Elasticsearch for faster searching and it can be scaled in multiple clusters easily if required

- I have used kafka for queuing data. We can introduce multiple data sources easily in future, all sources have to push data to the kafka topic. This gives strong foundation to entier data import mechanism.

- I have used logstash as “Consumer” it takes the data from Kafka TOPIC and pushes into the elastic Search (Simple 4-5 lines of configuration)

- Application is developed using Spring Boot Framework (java), that is also running within container


![alt text](https://raw.githubusercontent.com/ptprakhar/product_search_docker_package/master/architecture.png)


Running the application
========================
Entire application is running in the docker containers

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
http://localhost:8080/api/v1/product/search?productName=Brown%20eggs&productCategory=dairy%20-%20GET - GET

Kafka Kafdrop
localhost:9000

Elastic Search
localhost:9200

Sample Data
========================
Sample data can be found in sampleDataPayload.json, this data can be passed in body