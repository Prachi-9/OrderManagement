package com.orderManagementSystem.exception;

public class CustomerNotfoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	String errorMessage;
	
	public CustomerNotfoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String toString() {
		return errorMessage;
	}
}
