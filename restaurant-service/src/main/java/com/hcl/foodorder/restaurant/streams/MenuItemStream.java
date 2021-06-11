package com.hcl.foodorder.restaurant.streams;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


public interface MenuItemStream {
	String OUTPUT = "menu-item-output";

	@Output(OUTPUT)
	MessageChannel sendEmployeeMessage();
}
