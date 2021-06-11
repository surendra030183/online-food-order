package com.hcl.foodorder.domain.exception;


public class RestaurantDetailsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public RestaurantDetailsNotFoundException() {
		super();
	}

	public RestaurantDetailsNotFoundException(String message) {
		super(message);
	}
}