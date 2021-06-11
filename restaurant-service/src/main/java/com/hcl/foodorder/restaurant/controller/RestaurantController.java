package com.hcl.foodorder.restaurant.controller;

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

import com.hcl.foodorder.domain.exception.RestaurantDetailsNotFoundException;
import com.hcl.foodorder.domain.exception.RestaurantMenuCreationException;
import com.hcl.foodorder.domain.restaurant.MenuItem;
import com.hcl.foodorder.domain.restaurant.Restaurant;
import com.hcl.foodorder.restaurant.service.MenuService;
import com.hcl.foodorder.restaurant.service.RestaurantService;

@RestController
@RequestMapping("restaurants/v1")
public class RestaurantController {
	private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private MenuService menuMervice;

	@PostMapping("/create")
	public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
		logger.info("Invoking restaurant service create url");
		Restaurant restaurantDetails = restaurantService.createRestaurant(restaurant);
		return new ResponseEntity<>(restaurantDetails, HttpStatus.CREATED);
	}

	@GetMapping("/get/{restaurantId}")
	public ResponseEntity<Restaurant> get(@PathVariable("restaurantId") Long restaurantId)
			throws RestaurantDetailsNotFoundException {
		logger.info("Invoking get restaurant service url for {} ", restaurantId);
		Restaurant restaurantDetails = restaurantService.getRestaurant(restaurantId);
		return new ResponseEntity<>(restaurantDetails, HttpStatus.OK);
	}

	@PostMapping("/create/{restaurantId}/menu")
	public ResponseEntity<List<MenuItem>> createMenu(@RequestBody List<MenuItem> menuList,
			@PathVariable("restaurantId") Long restaurantId)
			throws RestaurantDetailsNotFoundException, RestaurantMenuCreationException {
		logger.info("Invoking Create Restaurant Menu {} ", restaurantId);
		Restaurant restaurantDetails = restaurantService.getRestaurant(restaurantId);
		if (restaurantDetails == null)
			throw new RestaurantMenuCreationException(
					"Menu Creation Failed for Restaurant. restaurantId " + restaurantId);
		List<MenuItem> result = menuMervice.createMenu(menuList, restaurantId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/get/{restaurantId}/menu")
	public ResponseEntity<List<MenuItem>> getMenuDetails(@PathVariable("restaurantId") Long restaurantId) {
		logger.info("Invoking get Restaurant Menu by Restaurantid {} ", restaurantId);
		List<MenuItem> menuList = menuMervice.getMenu(restaurantId);
		return new ResponseEntity<>(menuList, HttpStatus.OK);
	}
}
