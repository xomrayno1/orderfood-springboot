package com.demo.model;

import com.demo.utils.Status;

public class PagingSearchFilterProduct{
	private String keyword ;
	private long cateId ;
	private Status status ;
	private int pageSize = 5;
	private int page  ;
	
	
	
	public PagingSearchFilterProduct() {
		 
	}
	 
	public PagingSearchFilterProduct(String searchName, long cateId, Status status, int pageSize, int pageNumber) {
		 
		this.keyword = searchName;
		this.cateId = cateId;
		this.status = status;
		this.pageSize = pageSize;
		this.page = pageNumber;
	}

	 
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	 
	public int getPage() {
		if(page == 0 ) {
			return 0;
		}
		return page - 1;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PagingSearchFilterProduct [keyword=" + keyword + ", cateId=" + cateId + ", status=" + status
				+ ", pageSize=" + pageSize + ", pageNumber=" + page + "]";
	}
	

	
	
}
