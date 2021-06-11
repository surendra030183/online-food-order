package com.hcl.foodorder.driver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.foodorder.domain.driver.Driver;
import com.hcl.foodorder.domain.exception.DriverDetailsNotFoundException;
import com.hcl.foodorder.domain.exception.DuplicateDriverCreationException;
import com.hcl.foodorder.driver.service.DriverService;

@RestController
@RequestMapping("/drivers/v1")
public class DriverController {
	private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

	@Autowired
	private DriverService driverService;

	/**
	 * Create the new Driver
	 * 
	 * @param driver
	 * @return
	 * @throws DuplicateDriverCreationException
	 */
	@PostMapping("/create")
	public ResponseEntity<Driver> create(@Validated @RequestBody Driver driver)
			throws DuplicateDriverCreationException {
		logger.info("Invoking create driver api");
		return new ResponseEntity<>(driverService.create(driver), HttpStatus.CREATED);
	}

	/**
	 * Get the Driver Details by Driver Mobile Number.
	 * 
	 * @param mobileNumber
	 * @return
	 * @throws DriverDetailsNotFoundException
	 */
	@GetMapping("/get/{mobileNumber}")
	public ResponseEntity<Driver> getDriver(@PathVariable("mobileNumber") String mobileNumber)
			throws DriverDetailsNotFoundException {
		logger.info("Invoking driver get api by mobile number {} ",mobileNumber);
		return new ResponseEntity<>(driverService.getDriverDetails(mobileNumber), HttpStatus.OK);
	}

	/**
	 * Get the Available based on the Latitude,Longitude and Distance from
	 * Restaurant,Customer and Driver.
	 * 
	 * @param lat
	 * @param lon
	 * @param dis
	 * @return
	 */
	@GetMapping("/getavaiabledrivers")
	public ResponseEntity<List<Driver>> getAvaiableDrivers(@RequestParam(required = false) String lat,
			@RequestParam(required = false) String lon, @RequestParam(required = false) String dis) {
		logger.info("Invoking get all available drivers api.");
		if (lat == null || lon == null || dis == null) {
			logger.info("Feteching all the available drivers");
			return new ResponseEntity<>(driverService.getAllFreeDrivers(), HttpStatus.OK);
		}
		logger.info("Feteching the available drivers based on provided lat {}, lon {} and distance {} ",lat,lon,dis);
		return new ResponseEntity<>(driverService.getAllFreeDrivers(lat, lon, dis), HttpStatus.OK);
	}

	/**
	 * Update the Driver Details like Driver Current Status and Online Status by
	 * Driver Mobile Number.
	 * 
	 * @param driver
	 * @param mobileNumber
	 * @return
	 * @throws DriverDetailsNotFoundException
	 */
	@PutMapping("/update/{mobileNumber}")
	public ResponseEntity<Driver> getDriver(@RequestBody Driver driver,
			@PathVariable("mobileNumber") String mobileNumber) throws DriverDetailsNotFoundException {
		logger.info("Invoking update api to update driver details {} ",mobileNumber);
		driver.setMobileNumber(mobileNumber);
		return new ResponseEntity<>(driverService.updateDriverDetails(driver), HttpStatus.OK);
	}
}
