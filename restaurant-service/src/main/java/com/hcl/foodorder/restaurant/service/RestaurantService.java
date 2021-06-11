package com.hcl.foodorder.restaurant.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.foodorder.domain.exception.RestaurantDetailsNotFoundException;
import com.hcl.foodorder.domain.restaurant.Restaurant;
import com.hcl.foodorder.restaurant.repository.RestaurantRepository;


@Service
public class RestaurantService {
	private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);
	@Autowired
	private RestaurantRepository restaurantRepository;

	/**
	 * Save the new Restaurant details in Repository
	 * 
	 * @param restaurant
	 * @return
	 */
	public Restaurant createRestaurant(Restaurant restaurant) {
		logger.info("Save new Restaurant Details in Repository ");
		return restaurantRepository.save(restaurant);
	}

	/**
	 * Get the Restaurant details by RestaurantID and If not found It will throw an
	 * Exception
	 * 
	 * @param restaurantId
	 * @return
	 * @throws RestaurantDetailsNotFoundException
	 */
	public Restaurant getRestaurant(Long restaurantId) throws RestaurantDetailsNotFoundException {

		Optional<Restaurant> restaurantDetails = restaurantRepository.findById(restaurantId);
		if (restaurantDetails.isPresent())
			return restaurantDetails.get();
		else
			throw new RestaurantDetailsNotFoundException();
	}

}
