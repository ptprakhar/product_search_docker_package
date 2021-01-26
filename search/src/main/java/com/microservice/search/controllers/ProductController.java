package com.microservice.search.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.search.common.beans.ResponseResource;
import com.microservice.search.common.beans.Status;
import com.microservice.search.constants.CommonContants;
import com.microservice.search.dto.ProductDetail;
import com.microservice.search.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller class handles Product Search REST HTTP Requests
 * 
 * @author Prakhar
 *
 */
@RestController
@RequestMapping(path = { "/api/v1/product" })
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	/**
	 * Method to get list of products based on search query
	 * 
	 * @param productName
	 * @param productCategory
	 * @return
	 */
	@Operation(summary = "Search Products by Name and Category")
	
	@RequestMapping(value = "/search" , method = RequestMethod.GET)
	public ResponseResource<List<ProductDetail>> getProductsByNameAndCategory(
			@RequestParam("productName") String productName, @RequestParam("productCategory") String productCategory)
					throws Exception {
		List<ProductDetail> productDetail = null;

		logger.info("NEW PRODUCT SEARCH REQUEST");

		try {
			productDetail = productService.getProductsByNameAndCategory(productName, productCategory);
			return new ResponseResource<List<ProductDetail>>(ResponseResource.R_CODE_OK, "Success", productDetail,
					Status.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("ERROR::1002 SOME ISSUE IN SEARCHING THE DATA " + e.toString());
			return new ResponseResource<>(ResponseResource.R_CODE_ERROR, "Something Went Wrong! " + e.toString(),
					Status.FAILURE);
		}
	}



}
