package com.microservice.search.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.search.dto.ProductDetail;
import com.microservice.search.model.Product;

/**
 * All product search level business logic defined here
 * 
 * @author prakhar
 *
 */
@Service
public interface ProductService {

	/**
	 * Get product's data by Name and Category and order it by ratings (DESC)
	 * 
	 * @param productName
	 * @param productCategory
	 * @return
	 */
	public List<ProductDetail> getProductsByNameAndCategory(String productName, String productCategory);

	/**
	 * Method deletes entire data from elasticsearch's database
	 * 
	 * @return
	 */
	public void deleteAll();

}
