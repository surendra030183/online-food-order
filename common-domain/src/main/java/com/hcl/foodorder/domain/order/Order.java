package com.hcl.foodorder.domain.order;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.hcl.foodorder.domain.restaurant.MenuItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Order {
	@Id
	private Long id;
	private Long orderNumber;
	private Date createdDate;
	private Date lastUpdatedDate;
	private Set<MenuItem> itemList;
	private OrderStatus status;
	private Double total;
	private Double taxPercentage;
	private Double totalTaxAmount;
	private Long restaurantId;
	private Long customerId;
	private Long driverId;
}