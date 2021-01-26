package com.microservice.search.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.microservice.search.dto.ProductDetail;
import com.microservice.search.services.KafkaProducer;

/**
 * This Service implements the kafka producer, business logic
 * 
 * @author prakhar
 *
 */
@Service
public class KafkaProducerImpl implements KafkaProducer {

	@Autowired
	private KafkaTemplate<String, ProductDetail> kafkaProductTemplate;

	@Override
	public void sendTokafkaTopic(String topic, ProductDetail productDetail) {
			kafkaProductTemplate.send(topic, productDetail);		
	}

}
