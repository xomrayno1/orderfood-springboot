package com.demo.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entity.OrderDetail;
import com.demo.entity.Orders;
import com.demo.entity.Products;
import com.demo.model.CartItemModel;
import com.demo.model.OrderReponse;
import com.demo.service.ProductService;
import com.demo.utils.Constant;
import com.demo.utils.ConvertDTO;

@Controller
@RequestMapping("/api/v1/sells")
public class SellApiController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping(value = "/add", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addItemSell(
			@RequestBody CartItemModel cartItemModel,
			HttpServletRequest request){
		System.out.println(cartItemModel);
		HttpSession session = 	request.getSession();
		Object object =  session.getAttribute(Constant.SESSION_INVOICE);
		Products products = productService.getById(cartItemModel.getId());
		Orders orders = null;
		if(object == null) {
			 orders = new Orders();
			 List<OrderDetail> list  = new ArrayList<>();
			 list.add(new OrderDetail(products.getPrice(),products,cartItemModel.getCount()));
			 orders.setOrderDetails(list);
			 System.out.println("vao 1");
		}else {
			 orders = (Orders) object;
			 System.out.println("vao 2");
		}
		session.setAttribute(Constant.SESSION_INVOICE, orders);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/invoice")
	public ResponseEntity<Orders> addItemSell(HttpSession session){
		Orders orders =  (Orders) session.getAttribute(Constant.SESSION_INVOICE);
		//System.out.println(orders);
		//convert
		//OrderReponse orderReponse = ConvertDTO.convertOrderToDTO(orders);
		return new ResponseEntity<Orders>(orders,HttpStatus.OK);
	}
}
