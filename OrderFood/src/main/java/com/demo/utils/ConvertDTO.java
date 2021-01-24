package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

import com.demo.entity.OrderDetail;
import com.demo.entity.Orders;
import com.demo.model.OrderDetailResponse;
import com.demo.model.OrderReponse;

public class ConvertDTO {
	public static OrderReponse convertOrderToDTO(Orders orders) {
		if(orders != null) {
			OrderReponse orderReponse = new OrderReponse();
			//orderReponse.setId(orders.getId());
			orderReponse.setOrderDetails(null);
			orderReponse.setTotalPrice(orders.getTotalPrice());
			List<OrderDetailResponse> listODResponse = new ArrayList<>();
			orders.getOrderDetails().forEach(item ->{
				listODResponse.add(converOrderDetailToDTO(item));
			});
			orderReponse.setOrderDetails(listODResponse);
			return orderReponse;
		}
		return null;
	}
	public static OrderDetailResponse converOrderDetailToDTO(OrderDetail orderDetail) {
		if(orderDetail != null) {
			OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
			//orderDetailResponse.setId(orderDetail.getId());
			orderDetailResponse.setOrderId(orderDetail.getOrders().getId());
			orderDetailResponse.setPrice(orderDetail.getPrice());
			orderDetailResponse.setProId(orderDetail.getProducts().getId());
			orderDetailResponse.setProName(orderDetail.getProducts().getName());
			orderDetailResponse.setQuantity(orderDetail.getQuantity());
			return orderDetailResponse;
		}
		return null;
	}
}
