package com.microservice.search.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.microservice.search.common.beans.ResponseResource;
import com.microservice.search.common.beans.Status;
import com.microservice.search.dto.ProductDetail;
import com.microservice.search.services.DataImport;

@WebMvcTest(DataImportController.class)
public class DataImportControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DataImport dataImportService;
	
	@MockBean
	private DataImportController dataImportController;
	
	@Test
	public void importJsonDataTest() throws Exception {
		
		
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
		
		ResponseResource<Object> responseResource = new ResponseResource<>(
				ResponseResource.R_CODE_OK, "Something Went Wrong! ", Status.SUCCESS);
		
		when(dataImportController.importJsonData(prodListArr)).thenReturn(responseResource);
		
		String jsonBody = 
				"[{\n"
				+ "  \"name\": \"Brown eggs\",\n"
				+ "  \"category\": \"dairy\",\n"
				+ "  \"description\": \"Raw organic brown eggs in a basket\",\n"
				+ "  \"filename\": \"0.jpg\",\n"
				+ "  \"height\": 600,\n"
				+ "  \"width\": 400,\n"
				+ "  \"price\": 28.1,\n"
				+ "  \"rating\": 121212121\n"
				+ "}]";
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/v1/dataImport/pushBulkData")
				.contentType("application/json")
				.content(jsonBody);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Integer actual = result.getResponse().getStatus();
		assertEquals("200", actual.toString());
	}

}
