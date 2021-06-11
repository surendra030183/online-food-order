package com.hcl.foodorder.order.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hcl.foodorder.domain.exception.DuplicateOrderCreationException;
import com.hcl.foodorder.domain.exception.OrderDetailsNotFoundException;
import com.hcl.foodorder.domain.order.Order;
import com.hcl.foodorder.order.repository.OrderRepository;


@Service
public class OrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * Create new Order
	 * 
	 * @param order
	 * @return
	 * @throws DuplicateOrderCreationException
	 */
	public Order create(Order order) throws DuplicateOrderCreationException {
		logger.info("create service method");
		List<Order> existingOrder = orderRepository.findByOrderNumber(order.getOrderNumber());
		if (!CollectionUtils.isEmpty(existingOrder))
			throw new DuplicateOrderCreationException(
					"Duplicate Order Details Found For orderNumber=" + order.getOrderNumber());

		order.setCreatedDate(new Date());
		order.setLastUpdatedDate(new Date());
		return orderRepository.save(order);
	}

	/**
	 * Get the Orders by RestaurantID
	 * 
	 * @param restaurantId
	 * @return
	 * @throws OrderDetailsNotFoundException
	 */
	public List<Order> getOrdersByRestaurant(Long restaurantId) throws OrderDetailsNotFoundException {
		logger.info("Invoking getOrdersByRestaurant() service method ");
		List<Order> orderDetails = orderRepository.findByRestaurantId(restaurantId);
		if (CollectionUtils.isEmpty(orderDetails))
			throw new OrderDetailsNotFoundException("Order Details Not Available For restaurantId " + restaurantId);

		return orderDetails;
	}

	/**
	 * Get the list of orders by Customer ID
	 * 
	 * @param customerId
	 * @return
	 * @throws OrderDetailsNotFoundException
	 */
	public List<Order> getOrdersByCustomer(Long customerId) throws OrderDetailsNotFoundException {
		logger.info("Invoking getOrdersByCustomer() service method ");
		List<Order> orderDetails = orderRepository.findByCustomerId(customerId);
		if (CollectionUtils.isEmpty(orderDetails))
			throw new OrderDetailsNotFoundException("Order Details Not Available For customerId " + customerId);

		return orderDetails;
	}

	/**
	 * Get the Order Details by Order Number
	 * 
	 * @param orderNumber
	 * @return
	 * @throws OrderDetailsNotFoundException
	 */
	public List<Order> getOrdersByOrderNumber(Long orderNumber) throws OrderDetailsNotFoundException {
		logger.info("Invoking getOrdersByOrderNumber() service method {} ", orderNumber);
		List<Order> orderDetails = orderRepository.findByOrderNumber(orderNumber);
		if (CollectionUtils.isEmpty(orderDetails))
			throw new OrderDetailsNotFoundException("Order Details Not Available For orderNumber " + orderNumber);

		return orderDetails;
	}

}
