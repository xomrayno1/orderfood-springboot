package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value = {"","/index","/home"})
	public String home() {
		return "index/index";
	}
	
	 
 
}
