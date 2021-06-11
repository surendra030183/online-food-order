package com.hcl.foodorder.driver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hcl.foodorder.driver.service.DriverService;

@Component
public class CalculateDistanceUtil {
	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

	/**
	 * Calculate the distance between two points
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public double distance(double lat1, double lon1, double lat2, double lon2) {
		logger.info("calculating distance lat1 {}, lon1 {} and  lat2 {}, lon2 {} ", lat1, lon1, lat2, lon2);
		return Math.sqrt((lat2 - lat1) * (lat2 - lat1) + (lon2 - lon1) * (lon2 - lon1));
	}
}
