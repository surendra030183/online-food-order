package com.hcl.foodorder.domain.customer;

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
public class Customer {
	@Id
	private Long id;
	private String name;
	private String email;
	private String mobileNumber;
	private Address adress;
	private Boolean isActive;
}