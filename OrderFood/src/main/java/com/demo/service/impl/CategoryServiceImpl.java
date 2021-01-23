package com.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.entity.Category;
import com.demo.model.PagingSearchCategory;
import com.demo.model.entity.CategorySpecification;
import com.demo.repository.CategoryRepository;
import com.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository cateRepo;

	@Override
	public Category createCategory(Category category) {
		// TODO Auto-generated method stub
		category.setCreateDate(new Date());
		category.setUpdateDate(new Date());
		return cateRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		category.setUpdateDate(new Date());
		return cateRepo.save(category);
	}

	@Override
	public void deleteCategory(Category category) {
		// TODO Auto-generated method stub
		cateRepo.delete(category);
	}

	@Override
	public Category getById(long id) {
		// TODO Auto-generated method stub
		return cateRepo.findById(id).orElse(null);
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return cateRepo.findAll();
	}

	@Override
	public Page<Category> getAll(PagingSearchCategory pagingSearchCategory) {
		// TODO Auto-generated method stub
		return cateRepo.findAll(new CategorySpecification(pagingSearchCategory.getSearchName()),
								PageRequest.of(pagingSearchCategory.getPageNumber(), pagingSearchCategory.getPageSize()));
	}

	@Override
	public Category getByName(String name) {
		// TODO Auto-generated method stub
		return cateRepo.findByName(name);
	}

}
