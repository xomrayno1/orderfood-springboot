package com.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.demo.entity.Products;
import com.demo.service.ProductService;

@Component
public class ProductValidator implements Validator{

	@Autowired
	ProductService productService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == Products.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "name", "error.required");
		ValidationUtils.rejectIfEmpty(errors, "price", "error.required");
		
		Products products = (Products) target;
		
		if(products != null) {
			if(products.getName() != null) {
				Products oldProduct =  productService.getByName(products.getName());
				if(oldProduct != null) {
					if(oldProduct.getId() != products.getId()) {
						errors.rejectValue("name", "error.exists");
					}			
				}
			}
//			if(products.getCategory() != null) {
//				if(products.getCategory().getId() ==  0) {
//					errors.rejectValue("category", "error.required");
//				}
//			} 
			if(products.getCategory() == null) {	
				errors.rejectValue("category", "error.required");
			} 
		}
		
		
	}

}
