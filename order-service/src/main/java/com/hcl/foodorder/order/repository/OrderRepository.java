package com.hcl.foodorder.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcl.foodorder.domain.order.Order;


@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

	Optional<Order> findById(Long id);

	List<Order> findByOrderNumber(Long orderNumber);

	List<Order> findByRestaurantId(Long restaurantId);

	List<Order> findByCustomerId(Long customerId);

	List<Order> findByDriverId(Long driverId);

}
