package com.demo.model;

public class PagingSearchFilterProduct{
	private String searchName;
	private long cateId;
	private int pageSize;
	private int pageNumber;
	
	
	
	public PagingSearchFilterProduct() {
		 
	}
	public PagingSearchFilterProduct(String searchName, long cateId, int pageSize, int pageNumber) {
		 
		this.searchName = searchName;
		this.cateId = cateId;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public long getCateId() {
		return cateId;
	}
	public void setCateId(long cateId) {
		this.cateId = cateId;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	

	
	
}
