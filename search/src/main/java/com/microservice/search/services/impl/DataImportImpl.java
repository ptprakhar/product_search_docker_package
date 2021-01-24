package com.microservice.search.services.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.search.dto.ProductDetail;
import com.microservice.search.model.Product;
import com.microservice.search.services.DataImport;
import com.microservice.search.services.KafkaProducer;

/**
 * Service implements business logic of DataImport interface
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

	@Override
	public Integer getData(List<ProductDetail> productDetail) {
		
		for (ProductDetail val : productDetail) {
			
			ProductDetail productDetailDto = new ProductDetail();
			productDetailDto.setCategory(val.getCategory());
			productDetailDto.setName(val.getName());
			productDetailDto.setFilename(val.getFilename());
			productDetailDto.setHeight(val.getHeight());
			productDetailDto.setWidth(val.getWidth());
			productDetailDto.setPrice(val.getPrice());
			productDetailDto.setRating(val.getRating());
			
			kafkaProducer.sendTokafkaTopic("product_detail", productDetailDto);
		}
		return 1;
	}

}
