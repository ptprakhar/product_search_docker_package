package com.microservice.search.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microservice.search.dto.ProductDetail;
import com.microservice.search.services.KafkaProducer;
@Service
public class KafkaProducerImpl implements KafkaProducer {

	@Autowired
    private KafkaTemplate<String, ProductDetail> kafkaProductTemplate;
	
	@Override
	public void sendTokafkaTopic(String topic, ProductDetail productDetail) {
		kafkaProductTemplate.send(topic, productDetail);
	}

}
