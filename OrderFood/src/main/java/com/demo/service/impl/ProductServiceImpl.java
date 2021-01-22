package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.entity.Products;
import com.demo.model.PagingSearchFilterProduct;
import com.demo.model.entity.ProductSpecification;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepo;

	@Override
	public Products createProduct(Products products) {
		// TODO Auto-generated method stub
		 
		return productRepo.save(products);
	}

	@Override
	public Products updateProduct(Products products) {
		// TODO Auto-generated method stub
		return productRepo.save(products);
	}

	@Override
	public void deleteProducts(Products products) {
		// TODO Auto-generated method stub
		productRepo.delete(products);
	}

	@Override
	public Products getById(long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElse(null);
	}

	@Override
	public Products getByName(String name) {
		// TODO Auto-generated method stub
		return productRepo.findByName(name);
	}

	@Override
	public List<Products> getAll() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Page<Products> getAll(PagingSearchFilterProduct searchFilterProduct) {
		// TODO Auto-generated method stub
		return productRepo.findAll(new ProductSpecification(searchFilterProduct.getSearchName(), searchFilterProduct.getCateId()),
									PageRequest.of(searchFilterProduct.getPageNumber(), searchFilterProduct.getPageSize()));
	}
	

}
