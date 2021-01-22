package com.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
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

import com.demo.entity.Category;
import com.demo.exception.ResourceNotFoundException;
import com.demo.model.PagingSearchCategory;
import com.demo.service.CategoryService;
import com.demo.validator.CategoryValidator;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	CategoryValidator categoryValidator;
	
	@InitBinder
	public void dataBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == Category.class) {
			binder.setValidator(categoryValidator);
		}
	}
	
	@RequestMapping
	public String showCategory(ModelMap map,
			@RequestParam(required = false) String keyword ,
			@RequestParam(defaultValue = "1", required = false) int page) {
		 
		Page<Category> pageCate = 
					categoryService.getAll(new PagingSearchCategory(keyword,5,page - 1));
		map.addAttribute("keyword", keyword);
		map.addAttribute("pageCate", pageCate);
		map.addAttribute("message", map.getAttribute("message"));
		 
		return "category/category-list";
	}
	@GetMapping("/new")
	public String news(ModelMap map) {
		map.addAttribute("submitForm", new Category());
		return "category/category-action";
	}
	@GetMapping("/edit/{id}")
	public String edit(ModelMap map,@PathVariable("id")long id) {
		Category category = categoryService.getById(id);
		if(category == null) {
			throw new ResourceNotFoundException("category not found with id :" + id);
		}
		map.addAttribute("submitForm", category);
		return "category/category-action";
	}
	@GetMapping("/delete/{id}")
	public String delete(ModelMap map,@PathVariable("id")long id,
			RedirectAttributes redirectAttributes) {
		Category category = categoryService.getById(id);
		if(category == null) {
			throw new ResourceNotFoundException("category not found with id :" + id);
		}
	
		try {
			categoryService.deleteCategory(category);
			redirectAttributes.addFlashAttribute("message", "Xóa thành công");
		} catch (Exception e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("message", "Xóa thất bại");
		}
		return "redirect:/category";
	}
	@PostMapping("/save")
	public String save(ModelMap map,
			@Valid @ModelAttribute("submitForm") Category category,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "category/category-action";
		}
		if(category.getId() != 0) {
			try {
				categoryService.updateCategory(category);
				redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
			} catch (Exception e) {
				// TODO: handle exception
				redirectAttributes.addFlashAttribute("message", "Cập nhật thất bại");
			}
		}else {
			try {
				categoryService.createCategory(category);
				redirectAttributes.addFlashAttribute("message", "Thêm thành công");
			} catch (Exception e) {
				// TODO: handle exception
				redirectAttributes.addFlashAttribute("message", "Thêm thất bại");
			}
		}
		return "redirect:/category";
	}
	
}
