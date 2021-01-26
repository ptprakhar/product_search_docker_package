package com.microservice.search.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.microservice.search.common.beans.ResponseResource;
import com.microservice.search.common.beans.Status;
import com.microservice.search.dto.ProductDetail;
import com.microservice.search.services.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	@MockBean
	private ProductController productController;

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

		when(productService.getProductsByNameAndCategory("Corona", "beer")).thenReturn(prodListArr);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
		.get("/api/v1/product/search?productName=Corona&productCategory=beer");
		
		//String expected = "{\"responseCode\":200,\"message\":\"Success\",\"status\":\"SUCCESS\",\"data\":[{\"name\":\"Corona\",\"category\":\"beer\",\"description\":\"Dummy Description\",\"filename\":\"corona.jpg\",\"height\":50,\"width\":50,\"price\":100.5,\"rating\":5}]}";
		
		ResultActions actual = mockMvc.perform(requestBuilder);
		assertEquals(200, actual.andReturn().getResponse().getStatus());
		
	}
	
	@Test
	public void getProductsByNameAndCategoryTestNoParameters() throws Exception {
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
		when(productService.getProductsByNameAndCategory("Corona", "beer")).thenReturn(prodListArr);
		//not passing the parameters
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/v1/product/search");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//Should Return 400 HTT Code
		Integer actual = result.getResponse().getStatus();
		assertEquals("400", actual.toString());

	}
	
	/**
	 * Mocking a scenario when there is no data in elastic or elasticsearch is not responding 
	 * @throws Exception
	 */
	
	@Test 
	public void getProductsByNameAndCategoryTestCatchCase() throws Exception {
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
		
		ResponseResource<List<ProductDetail>> responseResource = new ResponseResource<>(
				ResponseResource.R_CODE_ERROR, "Something Went Wrong! ", Status.ERROR);
				
		responseResource.setData(prodListArr);
		
		when(productController.getProductsByNameAndCategory("Corona", "beer"))
		.thenReturn(responseResource);
				
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/v1/product/search?productName=Corona&productCategory=beer");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String actual = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String expected = "{\"responseCode\":500,\"message\":\"Something Went Wrong! \",\"status\":\"ERROR\",\"data\":[{\"name\":\"Corona\",\"category\":\"beer\",\"description\":\"Dummy Description\",\"filename\":\"corona.jpg\",\"height\":50,\"width\":50,\"price\":100.5,\"rating\":5}]}";
		
		assertEquals(expected, actual);
	}
	

	

}
