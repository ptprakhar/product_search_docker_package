package com.microservice.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.microservice.search.model.Product;

@Repository
public interface ProductRepositiry extends ElasticsearchRepository<Product, String> {

	List<Product> findByNameAndCategoryOrderByRatingDesc(String name, String category);
}
