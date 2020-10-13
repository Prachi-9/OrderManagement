package com.orderManagementSystem.exception;

import lombok.Data;

@Data
public class ExceptionJson {
	
	private String errorMessage;
	private String errorUri;
	
	public ExceptionJson() {
		super();
	}

	public ExceptionJson(String errorMessage, String errorUri) {
		super();
		this.errorMessage = errorMessage;
		this.errorUri = errorUri;
	}
	
	
	

}
