package com.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Orders implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal totalPrice;
	@Temporal(TemporalType.DATE)
	private Date createDate;
	@Temporal(TemporalType.DATE)
	private Date updateDate;
	@OneToMany(mappedBy = "orders")
	private List<OrderDetail> orderDetails;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getTotalPrice() {
		totalPrice = new BigDecimal(0);
		if(orderDetails != null) {
			orderDetails.forEach(item->{
				totalPrice = totalPrice.add(item.getSubPrice());
			});
		} 
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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
	public List<OrderDetail> getOrderDetails() {
		 
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", totalPrice=" + totalPrice + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", orderDetails=" + orderDetails + "]";
	}
	
	
}
