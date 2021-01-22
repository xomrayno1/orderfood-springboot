package com.demo.utils;

public enum Status {
	ACTIVE("Đang hoạt động"),
	IN_ACTIVE("Đã ngưng hoạt động");

	
	private String message;
	
	Status(String string) {
		// TODO Auto-generated constructor stub
		
		message = string;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
 
