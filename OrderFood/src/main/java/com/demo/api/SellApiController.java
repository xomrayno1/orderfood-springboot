package com.demo.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.demo.entity.OrderDetail;
import com.demo.entity.Orders;
import com.demo.entity.Products;
import com.demo.exception.ResourceNotFoundException;
import com.demo.model.CartItemModel;
import com.demo.model.OrderReponse;
import com.demo.service.ProductService;
import com.demo.utils.Constant;
import com.demo.utils.ConvertDTO;

@RestController
@RequestMapping("/api/v1/sells")
 
public class SellApiController {
	@Autowired
	ProductService productService;
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> addItemSell(@RequestBody CartItemModel cartItemModel
				,HttpSession session ){
		 System.out.println(cartItemModel);
		Products products = productService.getById(cartItemModel.getId());
		Object  object= session.getAttribute(Constant.SESSION_INVOICE);
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
				 if(item.getProducts().getId() == cartItemModel.getId()) {
					 item.setQuantity(cartItemModel.getCount()+ item.getQuantity()) ;
					 item.setSubPrice(item.getPrice().multiply(new BigDecimal(item.getQuantity()))); 
					 flag = false;
					 break;
				 }
			 }
			 if(flag) {
				 orders.getOrderDetails().add(new OrderDetail(products.getPrice(), products, cartItemModel.getCount()));
			 }	  
		}
		System.out.println(orders);
		session.setAttribute(Constant.SESSION_INVOICE, orders);
		return new ResponseEntity<Long>(Long.valueOf(orders.getOrderDetails().size()),HttpStatus.OK);
		  
	}
 
	@GetMapping("/invoice")
	public  ResponseEntity<OrderReponse>  getInvoice(
			 HttpSession session){
		Orders orders = (Orders) session.getAttribute(Constant.SESSION_INVOICE);
		OrderReponse orderReponse = ConvertDTO.convertOrderToDTO(orders);
		return new ResponseEntity<OrderReponse>(orderReponse, HttpStatus.OK);
	}
	
	@GetMapping("/count-item")
	public  ResponseEntity<Integer>  getCountItem(
			 HttpSession session){
		Orders orders = (Orders) session.getAttribute(Constant.SESSION_INVOICE);
		 if(orders != null) {
			 return new ResponseEntity<Integer>(orders.getOrderDetails().size(), HttpStatus.OK);
		 }
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}
	
	@PostMapping(value = "/delete-item",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<String>  deleteItem(@RequestBody Integer[] integers,
			 HttpSession session){
		Orders orders = (Orders) session.getAttribute(Constant.SESSION_INVOICE);
		 System.out.println(integers);
		if(orders != null) {
			if(orders.getOrderDetails() != null) {
				Iterator<OrderDetail> iterator = orders.getOrderDetails().iterator();
				OrderDetail item = null;
				while(iterator.hasNext()) {
					item = iterator.next();
					for(Integer integer : integers) {
						if(item.getProducts().getId() == integer) {
							iterator.remove();
							break;
						}
					}
				}
 
			}
			session.setAttribute(Constant.SESSION_INVOICE, orders);
			System.out.println(orders.getOrderDetails().size());
			return new ResponseEntity<String>("Xoá thành công", HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException("source not found");
		}
		
		  
		 
	}
	 
}
