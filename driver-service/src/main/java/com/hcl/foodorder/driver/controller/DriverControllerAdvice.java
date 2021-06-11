package com.hcl.foodorder.driver.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hcl.foodorder.domain.exception.DriverDetailsNotFoundException;
import com.hcl.foodorder.domain.exception.DuplicateDriverCreationException;
import com.hcl.foodorder.domain.exception.ErrorDetails;

@RestControllerAdvice
public class DriverControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(DriverControllerAdvice.class);
	@ExceptionHandler(DuplicateDriverCreationException.class)
	public ResponseEntity<ErrorDetails> orderDetailsNotFoundException(DuplicateDriverCreationException exception) {
		logger.info("Creating DuplicateDriverCreationException ErrorDetails.");
		ErrorDetails error = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DriverDetailsNotFoundException.class)
	public ResponseEntity<ErrorDetails> orderDetailsNotFoundException(DriverDetailsNotFoundException exception) {
		logger.info("Creating DriverDetailsNotFoundException ErrorDetails.");
		ErrorDetails error = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST) ;
	}
	
}
