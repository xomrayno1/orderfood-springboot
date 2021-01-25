package com.demo.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.entity.OrderDetail;
import com.demo.entity.Orders;
import com.demo.entity.Products;
import com.demo.model.CartItemModel;
import com.demo.service.ProductService;
import com.demo.utils.Constant;

@RestController
@RequestMapping("/api/v1/sells")
@SessionAttributes(Constant.SESSION_INVOICE)
public class SellApiController {
	@Autowired
	ProductService productService;
	@PostMapping(value = "/add", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> addItemSell(
			@RequestBody CartItemModel cartItemModel,
			HttpSession session){
		System.out.println(cartItemModel);
		 
		Object object =  session.getAttribute(Constant.SESSION_INVOICE);
		Products products = productService.getById(cartItemModel.getId());
		Orders orders = null;
		if(object == null) {
			 orders = new Orders();
			 List<OrderDetail> list  = new ArrayList<>();
			 list.add(new OrderDetail(products.getPrice(),products,cartItemModel.getCount()));
			 orders.setOrderDetails(list);
		 
		}else {
			 orders = (Orders) object; 
			 boolean flag = true;
			 for (OrderDetail item: orders.getOrderDetails()) {
				 if(item.getId() == cartItemModel.getId()) {
					  item.setQuantity(cartItemModel.getCount()+ item.getQuantity()) ;
					 flag = false;
					 break;
				 }
			 }
			 if(flag) {
				 orders.getOrderDetails().add(new OrderDetail(products.getPrice(), products, cartItemModel.getCount()));
			 }	  
			 
		}
		 session.setAttribute(Constant.SESSION_INVOICE, orders);
		 showInvoice1(session);
		return new ResponseEntity<Long>(Long.valueOf(orders.getOrderDetails().size()),HttpStatus.OK);
		  
	}
// 
	@GetMapping("/invoice")
	public  ResponseEntity<Orders> showInvoice(HttpServletRequest request){
		Orders orders = (Orders) request.getSession().getAttribute(Constant.SESSION_INVOICE);
		return new ResponseEntity<Orders>(orders, HttpStatus.OK);
	}
	public  void showInvoice1(HttpSession session){
		Orders orders = (Orders) session.getAttribute(Constant.SESSION_INVOICE);
		System.out.println(orders);
	}
	 
}
