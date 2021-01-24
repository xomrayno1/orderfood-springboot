package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.model.PagingSearchFilterProduct;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import com.demo.utils.Status;

@Controller
@RequestMapping("/sell")
public class SellController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@RequestMapping
	public String sell(ModelMap map,
			@ModelAttribute("searchForm") PagingSearchFilterProduct searchFilterProduct) {
		searchFilterProduct.setStatus(Status.ACTIVE);
		map.addAttribute("listCategory", categoryService.getAll());		
		map.addAttribute("pageProduct", productService.getAll(searchFilterProduct));
		return "sell/sell";
	}
	
	 
}
