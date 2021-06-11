package com.hcl.foodorder.domain.exception;


public class DuplicateCustomerCreationException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateCustomerCreationException() {
		super();
	}

	public DuplicateCustomerCreationException(String message) {
		super(message);
	}

}
