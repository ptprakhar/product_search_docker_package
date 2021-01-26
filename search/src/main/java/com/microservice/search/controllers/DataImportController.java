package com.microservice.search.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.search.common.beans.ResponseResource;
import com.microservice.search.common.beans.Status;
import com.microservice.search.dto.ProductDetail;
import com.microservice.search.services.DataImport;

import io.swagger.v3.oas.annotations.Operation;

/**
 * REST Controller to handle Data Import End-Points
 * 
 * @author prakhar
 *
 */
@RestController
@RequestMapping(path = { "/api/v1/dataImport" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class DataImportController {

	private static final Logger logger = LoggerFactory.getLogger(DataImportController.class);

	@Autowired
	private DataImport dataImport;

	/**
	 * Method to handle bulk JSON request
	 * 
	 * @param productDetail
	 * @return
	 */

	@Operation(summary = "Import Data in Bulk")
	@RequestMapping(value = "/pushBulkData", method = RequestMethod.POST)
	public ResponseResource<Object> importJsonData(@RequestBody List<ProductDetail> productDetail) {
		try {
			dataImport.pushProductDataToKafka(productDetail);
			logger.info("PUSHED NEW DATA TO KAFKA");
			return new ResponseResource<>(ResponseResource.R_CODE_OK,
					"Your data has been pushed to the queue sucessfully!", Status.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("ERROR::1001 _ " + e.toString());
			return new ResponseResource<>(ResponseResource.R_CODE_ERROR, "Fail " + e.toString(), Status.FAILURE);
		}

	}
}
