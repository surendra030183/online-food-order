package com.hcl.foodorder.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.foodorder.customer.service.CustomerService;
import com.hcl.foodorder.domain.customer.Customer;
import com.hcl.foodorder.domain.exception.CustomerDetailsNotFoundException;
import com.hcl.foodorder.domain.exception.DuplicateCustomerCreationException;

@RestController
@RequestMapping("/customers/v1/")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	/**
	 * On-Boarding New Customer create API.
	 * 
	 * @param customer
	 * @return
	 * @throws DuplicateCustomerCreationException
	 */
	@PostMapping("/create")
	public ResponseEntity<Customer> create(@RequestBody Customer customer) throws DuplicateCustomerCreationException {
		logger.info("Invoking customer create api");
		return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
	}

	/**
	 * Get the customer by customer mobile number.
	 * 
	 * @param customerMobileNumber
	 * @return
	 * @throws CustomerDetailsNotFoundException
	 */
	@GetMapping("/get/{customerMobileNumber}")
	public ResponseEntity<Customer> getByMobileNumber(@PathVariable("customerMobileNumber") String customerMobileNumber)
			throws CustomerDetailsNotFoundException {
		logger.info("Invoking customer get api by customer Mobile Number");
		return new ResponseEntity<>(customerService.getCustomerDetails(customerMobileNumber), HttpStatus.OK);
	}

	/**
	 * Update the Customer details by Customer Mobile Number.
	 * 
	 * @param customer
	 * @param customerMobileNumber
	 * @return
	 * @throws CustomerDetailsNotFoundException
	 */
	@PutMapping("/update/{customerMobileNumber}")
	public ResponseEntity<Customer> update(@RequestBody Customer customer,
			@PathVariable("customerMobileNumber") String customerMobileNumber) throws CustomerDetailsNotFoundException {
		logger.info("Invoking customer update api by customer Mobile Number");
		customer.setMobileNumber(customerMobileNumber);
		return new ResponseEntity<>(customerService.updateCustomerDetails(customer), HttpStatus.OK);
	}

}