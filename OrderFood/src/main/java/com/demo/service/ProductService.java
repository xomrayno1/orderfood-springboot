package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.entity.Products;
import com.demo.model.PagingSearchFilterProduct;
import com.demo.utils.Status;

public interface ProductService {
	Products createProduct(Products products);
	Products updateProduct(Products products);
	Products getByStatus(Status status);
	void deleteProducts(Products products);
	Products getById(long id);
	Products getByName(String name);
	List<Products> getAll();
	Page<Products> getAll(PagingSearchFilterProduct searchFilterProduct);
	
	
}
