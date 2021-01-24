package com.microservice.search.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.search.dto.ProductDetail;

@Service
public interface DataImport {

	/**
	 * Method to import data in batch
	 * @return
	 */
	public ProductDetail bathDataImport();
	
	
	/**
	 * Method to import using JSON List
	 * @return
	 */
	public Integer getData(List<ProductDetail> productDetail);
}
