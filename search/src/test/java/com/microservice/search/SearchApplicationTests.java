package com.microservice.search;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.microservice.search.controllers.ProductController;
import com.microservice.search.repository.ProductRepositiry;
import com.microservice.search.services.ProductService;

@SpringBootTest
class SearchApplicationTests {

	@Autowired
	private ProductController productController;

	@MockBean
	private ProductRepositiry prodRepo;

	
	@Test
	void contextLoads() {
		assertThat(productController).isNotNull();
	}

}
