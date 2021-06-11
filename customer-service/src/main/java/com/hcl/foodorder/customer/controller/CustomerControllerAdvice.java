/**
 * 
 */
package com.hcl.foodorder.customer.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hcl.foodorder.domain.exception.CustomerDetailsNotFoundException;
import com.hcl.foodorder.domain.exception.DuplicateDriverCreationException;
import com.hcl.foodorder.domain.exception.ErrorDetails;

@RestControllerAdvice
public class CustomerControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CustomerControllerAdvice.class);
	
	/**
	 * Creating CustomerDetailsNotFoundException to throw If the customer not available in the system.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(CustomerDetailsNotFoundException.class)
	public ResponseEntity<ErrorDetails> orderDetailsNotFoundException(CustomerDetailsNotFoundException exception) {
		logger.info("CustomerDetailsNotFoundException Message");
		ErrorDetails error = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST) ;
	}
	
	/**
	 * Creating DuplicateDriverCreationException to throw If the customer is already available in the system.
	 * To avoid the duplicate customer creation by Customer Mobile Number.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(DuplicateDriverCreationException.class)
	public ResponseEntity<ErrorDetails> orderDetailsNotFoundException(DuplicateDriverCreationException exception) {
		logger.info("DuplicateDriverCreationException Message");
		ErrorDetails error = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST) ;
	}
	
}
