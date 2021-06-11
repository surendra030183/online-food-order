package com.hcl.foodorder.domain.common;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Address {
	@Id
	private Long id;
	private AddressType type;
	private String street;
	private String city;
	private String state;
	private String pincode;
	private String lat;
	private String lon;
}
