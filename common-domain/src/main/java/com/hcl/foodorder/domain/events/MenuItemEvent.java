package com.hcl.foodorder.domain.events;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.hcl.foodorder.domain.restaurant.MenuItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemEvent {
	@Id
	private String eventId;
	private EventStatus status;
	private MenuItem payload;
	private Date createdDate;
	private Date updatedDate;
}
