package com.hcl.foodorder.domain.restaurant;

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
public class MenuItem {
	@Id
	private Long id;
	private Long restaurantId;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private Boolean isAvailable;
}
