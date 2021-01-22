package com.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Products;
import com.demo.exception.ResourceNotFoundException;
import com.demo.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductApiController {
	@Autowired
	ProductService productService;

	@GetMapping("/{id}")
	public ResponseEntity<Products> responseEntity(@PathVariable("id") long id){
		Products products = productService.getById(id);
		if(products  == null) {
			throw new ResourceNotFoundException("product not found exception with id : "+ id);
		}
		return new ResponseEntity<Products>(products, HttpStatus.OK);
	}
	
}
