package com.demo.model.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.demo.entity.Category;

public class CategorySpecification  implements Specification<Category>{

	private final String searchKey;
	
	public CategorySpecification(String searchKey) {
		 
		this.searchKey = searchKey;
	}

	@Override
	public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new LinkedList<Predicate>();
		 if(searchKey != null && !searchKey.trim().isEmpty()) {
			 String key = "%"+searchKey.trim()+"%";
			 Predicate preName = criteriaBuilder.like(root.get("name"), key);
			 predicates.add(preName);
			 System.out.println(key);
		 }

		return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
	}

	public String getSearchKey() {
		return searchKey;
	}

 

}
