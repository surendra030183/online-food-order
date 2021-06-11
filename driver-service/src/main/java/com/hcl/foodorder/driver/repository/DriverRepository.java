package com.hcl.foodorder.driver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcl.foodorder.domain.driver.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, Long>{
	Driver findByMobileNumber(String mobileNumber);
}
