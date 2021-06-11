package com.hcl.foodorder.driver.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.foodorder.domain.common.DriverStatus;
import com.hcl.foodorder.domain.driver.Driver;
import com.hcl.foodorder.domain.exception.DriverDetailsNotFoundException;
import com.hcl.foodorder.domain.exception.DuplicateDriverCreationException;
import com.hcl.foodorder.driver.repository.DriverRepository;
import com.hcl.foodorder.driver.util.CalculateDistanceUtil;

/**
 * This Service class contains logic to perform all business operations. and
 * Send the details to Repository.
 *
 */
@Service
public class DriverService {
	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private CalculateDistanceUtil calculateDistanceUtil;

	/**
	 * on-board new driver into system
	 * 
	 * @param driver
	 * @return
	 * @throws DuplicateDriverCreationException
	 */
	public Driver create(Driver driver) throws DuplicateDriverCreationException {
		logger.info("Invoking Driver create api");
		try {
			Driver existingDriver = getDriverDetails(driver.getMobileNumber());
			if (Objects.nonNull(existingDriver)) {
				logger.error("Duplicate driver creation for {} ", driver.getMobileNumber());
				throw new DuplicateDriverCreationException("Driver already available for " + driver.getMobileNumber());
			}
		} catch (DriverDetailsNotFoundException e) {
		}
		logger.info("new driver created {} ", driver.getMobileNumber());
		return driverRepository.save(driver);
	}

	/**
	 * get the Driver Details by Mobile Number.
	 * 
	 * @param mobileNumber
	 * @return
	 * @throws DriverDetailsNotFoundException
	 */
	public Driver getDriverDetails(String mobileNumber) throws DriverDetailsNotFoundException {
		logger.info("get the driver details for {} ",mobileNumber);
		Driver response = driverRepository.findByMobileNumber(mobileNumber);
		if (Objects.isNull(response))
			throw new DriverDetailsNotFoundException("No Driver Details Found for " + mobileNumber);
		logger.info("get existing driver details {} ", mobileNumber);
		return response;
	}

	/**
	 * get all available drivers whose DriverStatus is FREE
	 * 
	 * @return
	 */
	public List<Driver> getAllFreeDrivers() {
		logger.info("get all available free drives whose status is FREE");
		List<Driver> allDrivers = driverRepository.findAll();
		return allDrivers.stream().filter(driver -> {
			if (Objects.nonNull(driver.getCurrentLocation()))
				return (DriverStatus.FREE.name()).equals(driver.getCurrentLocation().getStatus().name());
			return false;
		}).collect(Collectors.toList());
	}

	/**
	 * Get the List of Drivers whose status is FREE and Distance between the driver
	 * current position and restaurant is less than equal to input distance KM.
	 * 
	 * @param lat
	 * @param lon
	 * @param inputDistance
	 * @return
	 */
	public List<Driver> getAllFreeDrivers(String lat, String lon, String inputDistance) {
		logger.info("get all available free drives based on lat {} ,lon {} and distance {} ",lat,lon,inputDistance);
		List<Driver> allDrivers = driverRepository.findAll();
		return allDrivers.stream().filter(driver -> {
			if (Objects.nonNull(driver.getCurrentLocation())) {
				Double driverLat = driver.getCurrentLocation().getLat();
				Double driverLon = driver.getCurrentLocation().getLon();

				Double inputLat = Double.parseDouble(lat);
				Double inputLon = Double.parseDouble(lon);
				Double result = calculateDistanceUtil.distance(inputLat, inputLon, driverLat, driverLon);
				BigDecimal distance = BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_DOWN);
				logger.info("Calculated Distance : {} for Driver Name : {} ", distance, driver.getName());
				return (DriverStatus.FREE.name()).equals(driver.getCurrentLocation().getStatus().name())
						&& distance.doubleValue() <= Double.parseDouble(inputDistance);
			}
			return false;
		}).collect(Collectors.toList());

	}

	/**
	 * Update the driver details
	 * 
	 * @param driver
	 * @return
	 * @throws DriverDetailsNotFoundException
	 */
	public Driver updateDriverDetails(Driver driver) throws DriverDetailsNotFoundException {
		Driver response = driverRepository.findByMobileNumber(driver.getMobileNumber());
		if (Objects.isNull(response))
			throw new DriverDetailsNotFoundException("No Driver Details Found for " + driver.getMobileNumber());
		logger.info("updating driver details {} ", driver.getMobileNumber());
		return driverRepository.save(driver);
	}

}
