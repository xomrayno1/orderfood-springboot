package com.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entity.Products;
import com.demo.exception.ResourceNotFoundException;
import com.demo.model.PagingSearchFilterProduct;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import com.demo.validator.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductValidator productValidator;
	@Autowired
	CategoryService categoryService;
 
	@InitBinder
	public void dataBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == Products.class) {
			binder.setValidator(productValidator);
		}
	}

	@RequestMapping
	public String showProduct(ModelMap map,
			@RequestParam(required = false) String keyword ,
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) long cateId) {
	 
		Page<Products> pageProduct = 
				productService.getAll(new PagingSearchFilterProduct(keyword,cateId,5,page - 1));
		map.addAttribute("keyword", keyword);  
		map.addAttribute("cateId", cateId);  
		map.addAttribute("pageProduct",pageProduct);
		map.addAttribute("message", map.getAttribute("message"));
		 
		 
		return "product/product-list";
	}
	@GetMapping("/new")
	public String news(Model map) {
		map.addAttribute("submitForm", new Products());
		initSelect(map);
		return "product/product-action";
	}
	@GetMapping("/edit/{id}")
	public String edit(Model map,@PathVariable("id")long id) {
		Products product = productService.getById(id);
		if(product == null) {
			throw new ResourceNotFoundException("product not found with id :" + id);
		}
		map.addAttribute("submitForm", product);
		initSelect(map);
		return "product/product-action";
	}
	@GetMapping("/delete/{id}")
	public String delete(ModelMap map,@PathVariable("id")long id,
			RedirectAttributes redirectAttributes) {
		Products product = productService.getById(id);
		if(product == null) {
			throw new ResourceNotFoundException("product not found with id :" + id);
		}
		try {
			productService.deleteProducts(product);
			redirectAttributes.addFlashAttribute("message", "Xóa thành công");
		} catch (Exception e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("message", "Xóa thất bại");
		}
		return "redirect:/products";
	}
	@PostMapping("/save")
	public String save(Model map,
			@Valid @ModelAttribute("submitForm") Products products,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			initSelect(map);
			return "product/product-action";
		}
		if(products.getId() != 0) {
			try {
				productService.updateProduct(products);
				redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
			} catch (Exception e) {
				// TODO: handle exception
				redirectAttributes.addFlashAttribute("message", "Cập nhật thất bại");
			}
		}else {
			try {
				productService.createProduct(products);
				redirectAttributes.addFlashAttribute("message", "Thêm thành công");
			} catch (Exception e) {
				// TODO: handle exception
				redirectAttributes.addFlashAttribute("message", "Thêm thất bại");
			}
		}
		return "redirect:/products";
	}
	public void initSelect(Model map) {
		map.addAttribute("listCategory",  categoryService.getAll());
	}
}
