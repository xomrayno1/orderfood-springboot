package com.demo.utils;

public enum Status {
	ACTIVE(1, "Đang hoạt động"),
	IN_ACTIVE(2, "Đã ngưng hoạt động");

	private int code;
	private String message;
	
	Status(int i, String string) {
		// TODO Auto-generated constructor stub
		code = i ;
		message = string;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
