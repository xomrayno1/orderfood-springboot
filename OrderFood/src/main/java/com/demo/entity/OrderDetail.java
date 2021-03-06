package com.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrderDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Products products;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders orders;
	private int quantity;
	private BigDecimal subPrice;
	@Temporal(TemporalType.DATE)
	private Date createDate;
	@Temporal(TemporalType.DATE)
	private Date updateDate;
	
	
	
	public OrderDetail() {
		 
	}
	public OrderDetail(BigDecimal price,Products products, int quantity) {
		this.price = price;
		this.products = products;
		this.quantity = quantity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		 
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	 
	public BigDecimal getSubPrice() {
	 
		this.subPrice = price.multiply(new BigDecimal(quantity));
		return subPrice;
	}
	public void setSubPrice(BigDecimal subPrice) {
		this.subPrice = subPrice;
	}
	
	
}
