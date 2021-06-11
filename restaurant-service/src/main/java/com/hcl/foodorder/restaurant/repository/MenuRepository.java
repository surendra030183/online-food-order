package com.hcl.foodorder.restaurant.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcl.foodorder.domain.restaurant.MenuItem;


@Repository
public interface MenuRepository extends MongoRepository<MenuItem, Long> {
	List<MenuItem> findByRestaurantId(Long restaurantId);
}
