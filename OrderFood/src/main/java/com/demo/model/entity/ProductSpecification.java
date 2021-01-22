package com.demo.model.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.demo.entity.Products;

public class ProductSpecification implements Specification<Products>{

	private final String searchKey;
	private final long  cateId;
	
	 

	public ProductSpecification(String searchKey, long cateId) {
		 
		this.searchKey = searchKey;
		this.cateId = cateId;
	}

	@Override
	public Predicate toPredicate(Root<Products> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new LinkedList<>();
		if(searchKey!=null && !searchKey.trim().isEmpty()) {
			String key = "%"+searchKey.trim()+"%";
			Predicate prediName = criteriaBuilder.equal(root.get("name"), key);
			predicates.add(prediName);
		}
		if(cateId != 0) {
			Predicate predicate = criteriaBuilder.equal(root.get("category"), cateId);
			predicates.add(predicate);
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
	}

	public String getSearchKey() {
		return searchKey;
	}

	public long getCateId() {
		return cateId;
	}

}
