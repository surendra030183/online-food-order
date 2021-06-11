package com.hcl.foodorder.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcl.foodorder.domain.customer.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long>{
	Customer findByMobileNumber(String mobileNumber);
}
