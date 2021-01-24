package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Products;
import com.demo.utils.Status;

@Repository
public interface ProductRepository  extends PagingAndSortingRepository<Products, Long>,
											JpaSpecificationExecutor<Products>{

	@Override
	List<Products> findAll();
	
	Products findByName(String name);
	Products findByStatus(Status status);
}
