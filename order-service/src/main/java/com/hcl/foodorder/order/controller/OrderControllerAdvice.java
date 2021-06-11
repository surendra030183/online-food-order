package com.hcl.foodorder.order.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hcl.foodorder.domain.exception.DuplicateOrderCreationException;
import com.hcl.foodorder.domain.exception.ErrorDetails;
import com.hcl.foodorder.domain.exception.OrderDetailsNotFoundException;


@RestControllerAdvice
public class OrderControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(OrderControllerAdvice.class);
	/**
	 * If the Requested Order Details not available based on order number will throw
	 * OrderDetailsNotFoundException
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(OrderDetailsNotFoundException.class)
	public ResponseEntity<ErrorDetails> orderDetailsNotFoundException(OrderDetailsNotFoundException exception) {
		logger.info("OrderDetailsNotFoundException ErrorDetails");
		ErrorDetails error = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.EXPECTATION_FAILED.name());
		return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);

	}

	/**
	 * If we try to create the duplicate order in the system by orderNumber will
	 * throw DuplicateOrderCreationException
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(DuplicateOrderCreationException.class)
	public ResponseEntity<ErrorDetails> duplicateOrderCreationException(DuplicateOrderCreationException exception) {
		logger.info("DuplicateOrderCreationException ErrorDetails");
		ErrorDetails error = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
