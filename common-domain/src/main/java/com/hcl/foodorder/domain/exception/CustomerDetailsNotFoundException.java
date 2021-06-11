package com.hcl.foodorder.domain.exception;


public class CustomerDetailsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerDetailsNotFoundException() {
		super();
	}

	public CustomerDetailsNotFoundException(String message) {
		super(message);
	}
}