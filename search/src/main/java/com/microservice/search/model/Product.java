package com.microservice.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.microservice.search.dto.ProductDetail;

/**
 * 
 * @author prakhar
 *
 */
@Document(indexName = "ps-data")
public class Product {

	@Id
	private String id;

	private String name;
	private String category;
	private String description;
	private String filename;
	private Integer height;
	private Integer width;
	private Double price;
	private Integer rating;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", description=" + description
				+ ", filename=" + filename + ", height=" + height + ", width=" + width + ", price=" + price
				+ ", rating=" + rating + "]";
	}

	/**
	 * Method to convert Product Entity to ProductDTO
	 * 
	 * @return productDetail
	 */

	public ProductDetail toProductDto() {
		ProductDetail productDetail = new ProductDetail();
		productDetail.setName(this.name);
		productDetail.setCategory(this.category);
		productDetail.setDescription(this.description);
		productDetail.setFilename(this.filename);
		productDetail.setHeight(this.height);
		productDetail.setWidth(this.width);
		productDetail.setPrice(this.price);
		productDetail.setRating(this.rating);
		return productDetail;
	}

}
