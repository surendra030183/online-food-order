package com.hcl.foodorder.customer.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.foodorder.customer.repository.CustomerRepository;
import com.hcl.foodorder.domain.customer.Customer;
import com.hcl.foodorder.domain.exception.CustomerDetailsNotFoundException;
import com.hcl.foodorder.domain.exception.DuplicateCustomerCreationException;

@Service
public class CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Implementing the Customer Create Business Logic.
	 * 
	 * @param customer
	 * @return
	 * @throws DuplicateCustomerCreationException
	 */
	public Customer create(Customer customer) throws DuplicateCustomerCreationException {
		logger.info("create service method");
		Customer result = customerRepository.findByMobileNumber(customer.getMobileNumber());
		if (Objects.nonNull(result)) {
			throw new DuplicateCustomerCreationException("Duplicate Customer Mobile Number");
		}
		return customerRepository.save(customer);
	}

	/**
	 * Get the Customer Details by Customer Mobile Number. If not found It will
	 * throw the CustomerDetailsNotFoundException
	 * 
	 * @param mobileNumber
	 * @return
	 * @throws CustomerDetailsNotFoundException
	 */
	public Customer getCustomerDetails(String mobileNumber) throws CustomerDetailsNotFoundException {
		logger.info("Invoking getCustomerDetails() service method ");
		Customer result = customerRepository.findByMobileNumber(mobileNumber);
		if (result == null) {
			throw new CustomerDetailsNotFoundException("Customer Details Not Fund");
		}
		return result;
	}

	/**
	 * Update the customer by Customer Mobile Number.
	 * 
	 * @param customer
	 * @return
	 * @throws CustomerDetailsNotFoundException
	 */
	public Customer updateCustomerDetails(Customer customer) throws CustomerDetailsNotFoundException {
		logger.info("Invoking updateCustomerDetails() service method ");
		Customer existingCustomer = getCustomerDetails(customer.getMobileNumber());
		if (Objects.nonNull(existingCustomer))
			return customerRepository.save(customer);
		return null;
	}

}
