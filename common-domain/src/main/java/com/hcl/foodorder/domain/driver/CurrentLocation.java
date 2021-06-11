package com.hcl.foodorder.domain.driver;

import org.springframework.data.mongodb.core.mapping.Document;

import com.hcl.foodorder.domain.common.DriverStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class CurrentLocation {
	private Double lat;
	private Double lon;
	private DriverStatus status;
}
