package com.hcl.foodorder.domain.exception;


public class DuplicateDriverCreationException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateDriverCreationException() {
		super();
	}

	public DuplicateDriverCreationException(String message) {
		super(message);
	}

}
