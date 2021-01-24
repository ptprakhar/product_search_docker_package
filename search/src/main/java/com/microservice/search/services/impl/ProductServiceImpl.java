package com.microservice.search.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.microservice.search.dto.ProductDetail;
import com.microservice.search.model.Product;
import com.microservice.search.repository.ProductRepositiry;
import com.microservice.search.services.ProductService;

/**
 * Service implements business logic of ProductService interface
 * @author prakhar
 *
 */

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepositiry productRepo;
	
	

	@Override
	public List<ProductDetail> getProductsByNameAndCategory(String productName, String productCategory) {
		
		List<Product> product = productRepo.findByNameAndCategoryOrderByRatingDesc(productName, productCategory);
		List<ProductDetail> productDetail = new ArrayList<>();
		
		for (Product val : product) {
			
			ProductDetail productDetailDto = new ProductDetail();
			productDetailDto.setCategory(val.getCategory());
			productDetailDto.setName(val.getName());
			productDetailDto.setFilename(val.getFilename());
			productDetailDto.setHeight(val.getHeight());
			productDetailDto.setWidth(val.getWidth());
			productDetailDto.setPrice(val.getPrice());
			productDetailDto.setRating(val.getRating());
			
			productDetail.add(productDetailDto);
		}

		return productDetail;
	}

	@Override
	public String deleteAll() {
		productRepo.deleteAll();
		return "All data Deleted";
	}

}
