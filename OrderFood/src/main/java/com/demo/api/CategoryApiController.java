package com.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Category;
import com.demo.exception.ResourceNotFoundException;
import com.demo.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryApiController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") long id){
		Category category = categoryService.getById(id);
		if(category == null) {
			throw new ResourceNotFoundException("Not found exception with id : "+id);
		}
		return new ResponseEntity<Category>(category,HttpStatus.OK);
	}

}
