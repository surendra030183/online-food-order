package com.hcl.foodorder.domain.exception;

public class RestaurantMenuCreationException extends Exception {

	private static final long serialVersionUID = 1L;

	public RestaurantMenuCreationException() {
		super();
	}

	public RestaurantMenuCreationException(String message) {
		super(message);
	}
}