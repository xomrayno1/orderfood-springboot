package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.entity.Category;
import com.demo.model.PagingSearchCategory;

public interface CategoryService {

	Category createCategory(Category category);
	Category updateCategory(Category category);
	void deleteCategory(Category category);
	Category getById(long id);
	Category getByName(String name);
	List<Category> getAll();
	Page<Category> getAll(PagingSearchCategory  pagingSearchCategory);
}
