package com.microservice.search.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.search.dto.ProductDetail;
import com.microservice.search.services.DataImport;
import com.microservice.search.services.ProductService;


/**
 * Controller class handles Product Search REST HTTP Requests
 * @author Prakhar
 *
 */
@RestController
@RequestMapping("/")
public class ProductController {
	
	@Autowired
	private ProductService productService;  
	
	@Autowired
	private DataImport dataImport; 
	

	/**
	 * Method to get list of products based on search query
	 * @param productName
	 * @param productCategory
	 * @return
	 */
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping("/searchProducts")
	public List<ProductDetail> getProductsByNameAndCategory(
			@RequestParam("productName") String productName, 
			@RequestParam("productCategory") String productCategory){
		return productService.getProductsByNameAndCategory(productName, productCategory);
	}
	
	
	
	/**
	 * Method to delete all the data from ElasticSearch
	 * @return
	 */
	@RequestMapping(value = "/deleteAllProducts", method = RequestMethod.GET)
    public String deleteAllProducts() {
        productService.deleteAll();
        return "All data Deleted";
    }
	
	
	@PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/getDataInBulk", method = RequestMethod.POST)
    public String uploadBulk(@RequestBody List<ProductDetail> productDetail) {
		dataImport.getData(productDetail);
	    return "Sent to Kafka ";
    }
		
}
