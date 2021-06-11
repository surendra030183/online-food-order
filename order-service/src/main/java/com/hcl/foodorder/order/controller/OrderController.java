package com.hcl.foodorder.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.foodorder.domain.exception.DuplicateOrderCreationException;
import com.hcl.foodorder.domain.exception.OrderDetailsNotFoundException;
import com.hcl.foodorder.domain.order.Order;
import com.hcl.foodorder.order.service.OrderService;


@RestController
@RequestMapping("orders/v1")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;

	/**
	 * create the new order by passing order details in the request body.
	 * 
	 * @param order
	 * @return
	 * @throws DuplicateOrderCreationException
	 */
	@PostMapping("/create")
	public ResponseEntity<Order> create(@RequestBody Order order) throws DuplicateOrderCreationException {
		logger.info("Invoking Order create api.");
		Order orderDetails = orderService.create(order);
		return new ResponseEntity<>(orderDetails, HttpStatus.CREATED);
	}

	/**
	 * Get the List of Orders by restaurantID
	 * 
	 * @param restaurantId
	 * @return
	 * @throws OrderDetailsNotFoundException
	 */
	@GetMapping("/get/restaurants/{restaurantId}")
	public ResponseEntity<List<Order>> getRestaurantOrders(@PathVariable("restaurantId") Long restaurantId)
			throws OrderDetailsNotFoundException {
		logger.info("Get the restaurant orders by restaurantId {} ", restaurantId);
		List<Order> orders = orderService.getOrdersByRestaurant(restaurantId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	/**
	 * Get the List of Orders by Customer Id to view his specific orders only.
	 * 
	 * @param customerId
	 * @return
	 * @throws OrderDetailsNotFoundException
	 */
	@GetMapping("/get/customers/{customerId}")
	public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable("customerId") Long customerId)
			throws OrderDetailsNotFoundException {
		logger.info("Get the orders by customerId {} ", customerId);
		List<Order> orders = orderService.getOrdersByCustomer(customerId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	/**
	 * Get the Order Details Based on the OrderNumber
	 * 
	 * @param orderNumber
	 * @return
	 * @throws OrderDetailsNotFoundException
	 */
	@GetMapping("/get/orders/{orderNumber}")
	public ResponseEntity<List<Order>> getOrders(@PathVariable("orderNumber") Long orderNumber)
			throws OrderDetailsNotFoundException {
		logger.info("Get the orders by orderNumber {} ", orderNumber);
		List<Order> orders = orderService.getOrdersByOrderNumber(orderNumber);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

}
