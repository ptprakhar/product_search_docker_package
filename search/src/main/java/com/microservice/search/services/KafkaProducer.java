package com.microservice.search.services;

import org.springframework.stereotype.Service;

import com.microservice.search.dto.ProductDetail;

@Service
public interface KafkaProducer {

	/**
	 * Method is used to send data to Kafka Topic
	 * 
	 * @param topic
	 * @return
	 */
	public void sendTokafkaTopic(String topic, ProductDetail productDetail);

}
