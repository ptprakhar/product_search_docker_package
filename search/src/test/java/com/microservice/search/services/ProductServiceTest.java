package com.microservice.search.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.microservice.search.model.Product;
import com.microservice.search.repository.ProductRepositiry;

@SpringBootTest
public class ProductServiceTest {
	
	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductRepositiry prodRepo;
	
	@Test
	public void getProductsByNameAndCategoryTest() {
		
		List<Product> prodListArr = new ArrayList<>();
		Product productData = new Product();
		productData.setName("Corona");
		productData.setCategory("beer");
		productData.setFilename("c.jpg");
		productData.setHeight(50);
		productData.setWidth(50);
		productData.setPrice(100.50);
		productData.setRating(5);
		//Adding 2 products
		prodListArr.add(productData);
		prodListArr.add(productData);
		
		when(prodRepo.findByNameAndCategoryOrderByRatingDesc("Corona", "beer")).thenReturn(prodListArr);
		
		
		//Service is returning 2 objects 
		assertEquals(2, productService.getProductsByNameAndCategory("Corona", "beer").size());	
	}
	
}
