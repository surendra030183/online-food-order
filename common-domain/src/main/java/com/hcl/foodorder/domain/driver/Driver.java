package com.hcl.foodorder.domain.driver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hcl.foodorder.domain.common.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Driver {
	@Id
	private Long id;
	private String name;
	private String email;
	private String mobileNumber;
	private Address adress;
	private CurrentLocation currentLocation;
	private Boolean isActive;
}
