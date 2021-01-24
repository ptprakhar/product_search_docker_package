package com.microservice.search.controllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.microservice.search.dto.ProductDetail;
import com.microservice.search.services.DataImport;
import com.microservice.search.services.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private DataImport dataImport;
	
	@Test
	public void getProductsByNameAndCategoryTest() throws Exception {
		
		List<ProductDetail> prodListArr = new ArrayList<>();
		ProductDetail productData = new ProductDetail();
		productData.setName("Corona");
		productData.setCategory("beer");
		productData.setDescription("Dummy Description");
		productData.setFilename("corona.jpg");
		productData.setHeight(50);
		productData.setWidth(50);
		productData.setPrice(100.50);
		productData.setRating(5);
		
		prodListArr.add(productData);
		
		when(productService.getProductsByNameAndCategory("Corona" , "beer")).thenReturn(prodListArr);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
		.get("/searchProducts?productName=Corona&productCategory=beer");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String actual = result.getResponse().getContentAsString();
		String expected = " [{\"name\":\"Corona\",\"category\":\"beer\",\"description\":\"Dummy Description\",\"filename\":\"corona.jpg\",\"height\":50,\"width\":50,\"price\":100.50,\"rating\":5}]";
		
		JSONAssert.assertEquals(expected, actual, false);
	}
	
	@Test
	public void deleteAllProductsTest() {
		assertTrue(true);
	}

}
