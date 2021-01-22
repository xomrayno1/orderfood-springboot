package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.entity.Orders;

public interface OrderRepository  extends  PagingAndSortingRepository<Orders, Long>{

}
