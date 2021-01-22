package com.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.demo.entity.Category;
import com.demo.service.CategoryService;

@Component
public class CategoryValidator implements Validator{
	
	@Autowired
	CategoryService categoryService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(Category.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Category category = (Category) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "error.required");
		if(category != null) {
			if(category.getName() != null) {
				Category oldCategory = categoryService.getByName(category.getName());
				if(oldCategory != null) {
					if(oldCategory.getId() != category.getId()){
						errors.rejectValue("name", "error.exists");
					}
				}
			}
		}
		
	}
	 
}
