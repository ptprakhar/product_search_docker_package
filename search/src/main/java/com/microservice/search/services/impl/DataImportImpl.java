package com.microservice.search.services.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.search.constants.CommonContants;
import com.microservice.search.dto.ProductDetail;
import com.microservice.search.model.Product;
import com.microservice.search.services.DataImport;
import com.microservice.search.services.KafkaProducer;

/**
 * Service implements business logic of DataImport interface
 * 
 * @author prakhar
 *
 */
@Service
public class DataImportImpl implements DataImport {

	@Autowired
	KafkaProducer kafkaProducer;

	@Override
	public ProductDetail bathDataImport() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method to push data to Kafka TOPIC in Loop
	 */
	@Override
	public void pushProductDataToKafka(List<ProductDetail> productDetail) {

		for (ProductDetail val : productDetail) {
			kafkaProducer.sendTokafkaTopic(CommonContants.PRODUCT_KAFKA_TOPIC, val);
		}
		
	}

}
