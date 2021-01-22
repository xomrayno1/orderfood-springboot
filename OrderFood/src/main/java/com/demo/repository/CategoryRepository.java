package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Category;

@Repository
public interface CategoryRepository  extends
						PagingAndSortingRepository<Category, Long>,
						JpaSpecificationExecutor<Category>{
	
	@Override
	List<Category> findAll();
	Category findByName(String name);
}
