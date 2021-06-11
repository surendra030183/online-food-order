package com.hcl.foodorder.domain.exception;


public class DriverDetailsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DriverDetailsNotFoundException() {
		super();
	}

	public DriverDetailsNotFoundException(String message) {
		super(message);
	}
}